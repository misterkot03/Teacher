require: slotfilling/slotFilling.sc
module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Привет! Я учебный консультант. Спросите, что вы хотите узнать.

    state: Hello
        intent!: /привет
        a: Привет! Как я могу помочь?

    state: Bye
        intent!: /пока
        a: До встречи! Если будут вопросы, обращайтесь.
