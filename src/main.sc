require: slotfilling/slotFilling.sc
 module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Добро пожаловать! Вы можете спросить, например: "Дай определение термина Искусственный интеллект" или "Объясни Алгоритм".
    
    state: termDefinition
        intent!: /define_term
        script:
            // Проверяем, что слот Term распознан
            // Обычно для обязательного слота при успешном распознавании 
            // значение будет доступно в $parseTree._Term.value
            if ($parseTree.Term && $parseTree.Term.value) {
                $session.termName = $entities.Term;
              
    
                // Справочник определений
                $session.termData = JSON.parse($parseTree.Term[0].value);
    
              
            $session.response = termData.definition;
            
         } 
        a: {{$session.response}}
        a: {{$parseTree.MalfunctionName}}
        a: {{$parseTree._Term.valu}}
        a: {{$parseTree._Term}}
        a: {{$parseTree.Term.value}}
        a: {{$parseTree._Term.value}}
        a: {{$entities.Term}}
        a: {{JSON.stringify($parseTree)}}
    state: NoMatch
        event!: noMatch
        a: Извините, я не понял ваш запрос. Попробуйте сформулировать его иначе.
