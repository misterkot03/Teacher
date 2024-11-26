require: slotfilling/slotFilling.sc
 module = sys.zb-common
theme: /

    state: AiQuestion
        intent!: /ai_question
        a: Искусственный интеллект — это область компьютерных наук, направленная на создание систем, которые имитируют человеческий интеллект.

    state: MlQuestion
        intent!: /ml_question
        a: Машинное обучение — это метод обучения компьютеров находить закономерности в данных без явного программирования.

    state: ProgrammingQuestion
        intent!: /programming_question
        a: Для начала изучите основы языка программирования, такого как Python или JavaScript. Рекомендуются онлайн-курсы, такие как Codecademy или Coursera.
        
    state: Нет совпадений
        event!: noMatch
        a: Извините, я не понял.
