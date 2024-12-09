require: slotfilling/slotFilling.sc
    module = sys.zb-common
theme: /
    
    state: Start
        q!: $regex</start>
        a: Добро пожаловать! Вы можете спросить, например: "Дай определение термина Искусственный интеллект" или "Объясни Алгоритм".
        
    state: termDefinition
      intent!: /define_term
      script:
        // Проверяем, есть ли сущности в запросе
        if ($entities && $entities.length > 0) {
            // Берем первую сущность, которая соответствует термину
            var termData = JSON.parse($entities[0].value); // Парсим значение сущности
            var termName = termData.name; // Получаем имя термина
            
            // Формируем URL для запроса к серверу с нужным термином
            var url = 'http://51.250.4.123:8001/va-in-edu/get-resource-by-query?keywords=' + encodeURIComponent(termName) + '&types=term';
            
            // Логирование URL
            log("Запрашиваем URL: " + url); // Логируем ссылку
            
            // Выполняем запрос к серверу и обрабатываем ответ
            var serverResponse = $http.get(url);
            log("Ответ от сервера: " + JSON.stringify(serverResponse)); // Логируем весь ответ от сервера
    
            if (serverResponse && serverResponse.data && serverResponse.data.length > 0) {
                // Ищем определение термина в ответе
                var term = null;
                for (var i = 0; i < serverResponse.data.length; i++) {
                    if (serverResponse.data[i].name.toLowerCase() === termName.toLowerCase()) {
                        term = serverResponse.data[i];
                        break;
                    }
                }
    
                if (term && term.text) {
                    $session.response = term.text; // Если термин найден, выводим описание
                } else {
                    $session.response = "Извините, я не нашел определения для данного термина.";
                }
            } else {
                $session.response = "Извините, я не нашел информацию по запросу.";
            }
        } else {
            $session.response = "Извините, я не смог распознать термин. Повторите, пожалуйста.";
        }
      a: {{$session.response}}
    
    state: NoMatch
        event!: noMatch
        a: Извините, я не понял ваш запрос. Попробуйте сформулировать его иначе.
