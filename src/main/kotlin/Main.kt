import NoteService.add
import NoteService.createComment
import NoteService.delete
import NoteService.deleteComment
import NoteService.edit
import NoteService.editComment
import NoteService.getById
import NoteService.getComments
import NoteService.printNotes
import NoteService.restoreComment
import data.Comment
import data.Note

fun main(){
    val note = Note()
    val comment = Comment()
    val note1 = note.copy(
        text = "Говядина - 500 г\n" +
                "Свёкла - 1 шт.\n" +
                "Картофель - 2 шт.\n" +
                "Капуста белокочанная - 200 г\n" +
                "Морковь - 1 шт.\n" +
                "Лук репчатый - 1 шт.\n" +
                "Томатная паста - 1 ст. ложка\n" +
                "Масло растительное - 2 ст. ложки\n" +
                "Уксус - 1 ч. ложка\n" +
                "Лавровый лист - 1 шт.\n" +
                "Перец чёрный горошком - 2-3 шт.\n" +
                "Соль - 2 ч. ложки (по вкусу)\n" +
                "Вода - 1,5 л\n" +
                "Зелень укропа и/или петрушки (для подачи) - 3-4 веточки\n" +
                "Сметана (для подачи) - 2 ст. ложки ",
        title = "Рецепт борща:",
        ownerId = 1,
        comments = mutableListOf(),
        deletedComments = mutableListOf()
    )
    val note2 = note.copy(
        text = "Мясо (курица, баранина и т.д.) - 1 кг\n" +
                "Рис (длиннозерный пропаренный или другой рис) - 1 кг\n" +
                "Морковь - 1 кг\n" +
                "Лук репчатый - 4 шт.\n" +
                "Чеснок - 2 головки\n" +
                "Перец острый свежий - 2 стручка\n" +
                "Зира или другие пряности - по вкусу\n" +
                "Соль - по вкусу\n" +
                "Масло растительное - 250-300 мл",
        title = "Рецепт плова",
        ownerId = 2,
        comments = mutableListOf(),
        deletedComments = mutableListOf()


    )
    val note3 = note.copy(
        text = "Креветки тигровые — 100 г\n" +
                "Галангал (корень) — 15 г\n" +
                "Лемонграсс — 10 г\n" +
                "Лук-шалот — 50 г\n" +
                "Паста \"Том Ям\" — 2 ст. ложки\n" +
                "Помидоры черри — 6 шт.\n" +
                "Шампиньоны — 6 шт.\n" +
                "Перец чили свежий — 5 г\n" +
                "Лайм — 1 шт.\n" +
                "Кокосовое молоко (или сливки) — 250 мл\n" +
                "Бульон куриный — 200 мл\n" +
                "Сахар тростниковый — 1 ч. ложка\n" +
                "Масло растительное — 10 мл\n" +
                "Кинза — 5 г\n" +
                "Лапша \"стеклянная\" — 100 г",
        title = "Рецепт Том-Ям",
        ownerId = 2,
        comments = mutableListOf(),
        deletedComments = mutableListOf()
    )
    val note4 = note.copy(
        text = "Перец болгарский красный (резаный полосками) - 2 шт.\n" +
                "Перец болгарский желтый (резаный полосками) - 1 шт.\n" +
                "Помидоры (резаные кружочками) - 4 шт.\n" +
                "Масло оливковое - 1 ч. л.\n" +
                "Лук шалот (мелко рубленный) - 2 шт.\n" +
                "Чеснок (измельченный) - 2 зубка\n" +
                "Базилик свежий (рубленый) - 2 ст. л.\n" +
                "Перец черный молотый - по вкусу\n" +
                "Базилик для сервировки",
        title = "Рецепт холодной закуски из помидоров и болгарского перца",
        ownerId = 1,
        comments = mutableListOf(),
        deletedComments = mutableListOf()
    )
    val note5 = note.copy(
        text = "Мука - 1,75 стакана\n" +
                "Яйца - 2 шт.\n" +
                "Кофе крепкий - 1 стакан\n" +
                "Пахта или кислое молоко - 1 стакан\n" +
                "Масло растительное - 1/2 стакана\n" +
                "Ванильный экстракт - 1 ч. л.\n" +
                "Сахар белый - 2 стакана\n" +
                "Какао-порошок - 0,75 стакана\n" +
                "Сода - 1-2 ч. л.\n" +
                "Разрыхлитель - 1 ч. л.\n" +
                "Соль - 0,5-1 ч. л.\n" +
                "Шоколад - 200 г\n" +
                "Масло сливочное - 2 ст. л.",
        title = "Рецепт шоколадного торта",
        ownerId = 1,
        comments = mutableListOf(),
        deletedComments = mutableListOf()
    )
    val editedNote = note.copy(
        id = 1,
        text = "Говядина - 500 г\n" +
                "Свёкла - 1 шт.\n" +
                "Картофель - 2 шт.\n" +
                "Капуста белокочанная - 200 г\n" +
                "Морковь - 1 шт.\n" +
                "Лук репчатый - 1 шт.\n" +
                "Томатная паста - 1 ст. ложка\n" +
                "Масло растительное - 2 ст. ложки\n" +
                "Уксус - 1 ч. ложка\n" +
                "Лавровый лист - 1 шт.\n" +
                "Перец чёрный горошком - 2-3 шт.\n" +
                "Соль - 2 ч. ложки (по вкусу)\n" +
                "Вода - 1,5 л\n" +
                "Зелень укропа и/или петрушки (для подачи) - 3-4 веточки\n" +
                "Сметана (для подачи) - 2 ст. ложки\n" +
                "Вкусный красный борщ с говядиной, приготовить очень легко",
        title = "Обновленный рецепт борща"

    )
    val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
    val comment2 = comment.copy(noteId = 1, message = "Легко и просто готовится, спасибо!")
    val comment3 = comment.copy(noteId = 2, message = "Обожаю плов")
    val comment4 = comment.copy(noteId = 3, message = "Спасибо за рецепт")
    val editedComment1 = comment.copy(id = 1, message = "Очень вкусно, спасибо за рецепт. Обязательно посоветую друзьям")

    add(note1)
    add(note2)
    add(note3)
    add(note4)
    add(note5)
    edit(editedNote)
    createComment(comment1)
    createComment(comment2)
    createComment(comment3)
    createComment(comment4)
    delete(5)
    deleteComment(2)
    editComment(editedComment1)
    println("Проверка метода по получению заметки по id")
    getById(1)
    println("Проверка метода по получению списка комментариев")
    getComments(2)
    restoreComment(2)
    printNotes()

}