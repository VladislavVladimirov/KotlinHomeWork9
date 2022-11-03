import data.Comment
import data.Note
import exception.CommentNotFoundException
import exception.NoteNotFoundException
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

internal class NoteServiceTest {
    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun add() {
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
        NoteService.add(note1)
        val notes = NoteService.get()
        assertEquals(1, notes[0].id)
    }

    @Test
    fun createCommentTrue() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        NoteService.add(note1)
        val result = NoteService.createComment(comment1)
        assertTrue(result)

    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentShouldThrow() {
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
        val comment1 = comment.copy(noteId = 10, message = "Очень вкусно, спасибо за рецепт!")
        NoteService.add(note1)
        NoteService.createComment(comment1)
    }

    @Test
    fun deleteTrue() {
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
        NoteService.add(note1)
        val result = NoteService.delete(1)
        assertTrue(result)
    }

    @Test
    fun deleteFalse() {
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
        NoteService.add(note1)
        val result = NoteService.delete(2)
        assertFalse(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteShouldThrow() {
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
        NoteService.add(note1)
        NoteService.delete(1)
        NoteService.delete(1)
    }

    @Test
    fun get() {
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
            comments = mutableListOf()
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
        val check = mutableListOf<Note>()
        NoteService.add(note1)
        NoteService.add(note2)
        val notes = NoteService.get()
        assertNotEquals(notes, check)
    }

    @Test
    fun deleteCommentTrue() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        val result = NoteService.deleteComment(1)
        assertTrue(result)
    }

    @Test
    fun deleteCommentFalse() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        val result = NoteService.deleteComment(2)
        assertFalse(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentShouldThrow() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        NoteService.deleteComment(1)
        NoteService.deleteComment(1)

    }

    @Test
    fun editTrue() {
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
        NoteService.add(note1)
        val result = NoteService.edit(editedNote)
        assertTrue(result)

    }

    @Test(expected = NoteNotFoundException::class)
    fun editShouldThrow() {
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
        val editedNote = note.copy(
            id = 2,
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
        NoteService.add(note1)
        NoteService.edit(editedNote)


    }

    @Test(expected = NoteNotFoundException::class)
    fun editShouldThrowDeleted() {
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
        NoteService.add(note1)
        NoteService.delete(1)
        NoteService.edit(editedNote)
    }

    @Test
    fun editCommentTrue() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        val editedComment1 =
            comment.copy(id = 1, message = "Очень вкусно, спасибо за рецепт. Обязательно посоветую друзьям")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        val result = NoteService.editComment(editedComment1)
        assertTrue(result)

    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentShouldThrowDoesNotExist() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        val editedComment1 =
            comment.copy(id = 2, message = "Очень вкусно, спасибо за рецепт. Обязательно посоветую друзьям")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        NoteService.editComment(editedComment1)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentShouldThrowDeletedComment() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        val editedComment1 =
            comment.copy(id = 1, message = "Очень вкусно, спасибо за рецепт. Обязательно посоветую друзьям")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        NoteService.deleteComment(1)
        NoteService.editComment(editedComment1)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentShouldThrowDeletedNote() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        val editedComment1 =
            comment.copy(id = 1, message = "Очень вкусно, спасибо за рецепт. Обязательно посоветую друзьям")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        NoteService.delete(1)
        NoteService.editComment(editedComment1)
    }

    @Test
    fun getById() {
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
        NoteService.add(note1)
        NoteService.add(note2)
        val result = (NoteService.getById(2)).toString()
        assertNotEquals("", result)

    }

    @Test
    fun getComments() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        val comment2 = comment.copy(noteId = 1, message = "Легко и просто готовится, спасибо!")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        NoteService.createComment(comment2)
        val result = (NoteService.getComments(1)).toString()
        assertNotEquals("", result)

    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsThrowsDeleted() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        val comment2 = comment.copy(noteId = 1, message = "Легко и просто готовится, спасибо!")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        NoteService.createComment(comment2)
        NoteService.delete(1)
        NoteService.getComments(1)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsThrowsDoesNotExist() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        val comment2 = comment.copy(noteId = 1, message = "Легко и просто готовится, спасибо!")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        NoteService.createComment(comment2)
        NoteService.getComments(2)
    }

    @Test
    fun restoreCommentTrue() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        val comment2 = comment.copy(noteId = 1, message = "Легко и просто готовится, спасибо!")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        NoteService.createComment(comment2)
        NoteService.deleteComment(2)
        val result = NoteService.restoreComment(2)
        assertTrue(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentThrowsDoesNotExist() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        val comment2 = comment.copy(noteId = 1, message = "Легко и просто готовится, спасибо!")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        NoteService.createComment(comment2)
        NoteService.deleteComment(2)
        NoteService.restoreComment(3)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentThrowsNoteDeleted() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        val comment2 = comment.copy(noteId = 1, message = "Легко и просто готовится, спасибо!")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        NoteService.createComment(comment2)
        NoteService.deleteComment(2)
        NoteService.delete(1)
        NoteService.restoreComment(3)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentThrowsAlreadyRestored() {
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
        val comment1 = comment.copy(noteId = 1, message = "Очень вкусно, спасибо за рецепт!")
        val comment2 = comment.copy(noteId = 1, message = "Легко и просто готовится, спасибо!")
        NoteService.add(note1)
        NoteService.createComment(comment1)
        NoteService.createComment(comment2)
        NoteService.deleteComment(2)
        NoteService.restoreComment(2)
        NoteService.restoreComment(2)
    }
}