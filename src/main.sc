require: slotfilling/slotFilling.sc
 module = sys.zb-common
theme: /

state: Start
    q!: $regex</start>
    a: Добро пожаловать! Вы можете спросить: "Дай определение [термина]" или "Объясни [термин]".

# Определение термина
state: termDefinition
    intent!: /Определение термина
    a: Сейчас найду определение для термина "{{Термин}}"...
    script:
        if (context.entity("Термин") == "Искусственный интеллект") {
            $replies.push("Искусственный интеллект (ИИ) — это область информатики, изучающая создание систем, способных решать задачи, требующие человеческого интеллекта.");
        } else if (context.entity("Термин") == "Алгоритм") {
            $replies.push("Алгоритм — это последовательность действий, выполняемых для решения задачи.");
        } else if (context.entity("Термин") == "Полиморфизм") {
            $replies.push("Полиморфизм — это принцип объектно-ориентированного программирования, позволяющий методам работать с различными типами данных.");
        } else if (context.entity("Термин") == "Машинное обучение") {
            $replies.push("Машинное обучение (ML) — это область искусственного интеллекта, которая изучает методы, позволяющие обучать модели на основе данных.");
        } else if (context.entity("Термин") == "Инкапсуляция") {
            $replies.push("Инкапсуляция — это процесс скрытия деталей реализации объекта и предоставление интерфейса для работы с ним.");
        } else {
            $replies.push("Извините, я пока не знаю определения для термина '{{Термин}}'.");
        }

# Объяснение термина
state: termExplanation
    intent!: /Объяснение термина
    a: Сейчас объясню, что такое "{{Термин}}"...
    script:
        if (context.entity("Термин") == "Искусственный интеллект") {
            $replies.push("ИИ включает в себя машинное обучение, обработку естественного языка и компьютерное зрение.");
        } else if (context.entity("Термин") == "Алгоритм") {
            $replies.push("Алгоритмы используются во многих сферах, включая программирование, математику и обработку данных.");
        } else if (context.entity("Термин") == "Полиморфизм") {
            $replies.push("Примером полиморфизма является возможность вызова одного и того же метода для объектов разных классов.");
        } else if (context.entity("Термин") == "Машинное обучение") {
            $replies.push("В машинном обучении используются данные и алгоритмы для построения предсказательных моделей.");
        } else if (context.entity("Термин") == "Инкапсуляция") {
            $replies.push("Инкапсуляция защищает данные объекта от прямого доступа извне, позволяя избежать ошибок.");
        } else {
            $replies.push("Пока не могу объяснить, что такое '{{Термин}}'.");
        }

# Обработка непонятых запросов
state: NoMatch
    event!: noMatch
    a: Извините, я не понял ваш запрос. Попробуйте сформулировать его иначе.
