require: slotfilling/slotFilling.sc
module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Добро пожаловать! Спросите, что хотите узнать.

    state: Hello
        intent!: /привет
        a: Приветствую! Чем могу помочь?

    state: Bye
        intent!: /пока
        a: До свидания!

    state: KnowledgeBase
        intentGroup!: /KnowledgeBase
        a: Вы хотите найти {{категория}}. Сейчас поищу.
        script:
            var category = context.entity("категория");
            if (category == "видео") {
                $replies.push("Вот видео по запросу: [ссылка]");
            } else if (category == "статья") {
                $replies.push("Вот статья: [ссылка]");
            } else {
                $replies.push("Не удалось найти {{категория}}.");
            }

    state: NoMatch
        event!: noMatch
        a: Простите, я вас не понял. Скажите по-другому.
