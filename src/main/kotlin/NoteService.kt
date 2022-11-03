import data.Comment
import data.Note
import exception.CommentNotFoundException
import exception.NoteNotFoundException
import java.text.SimpleDateFormat

object NoteService {
    private var id = 1
    private var commentId = 1
    private var notes = mutableListOf<Note>()
    private var deletedNotes = mutableListOf<Note>()

    fun clear() {
        notes = mutableListOf<Note>()
        deletedNotes = mutableListOf<Note>()
        id = 1
        commentId = 1
    }

    fun get(): MutableList<Note> {
        return notes
    }

    fun add(note: Note): Note {
        notes += note.copy(id = id++, date = System.currentTimeMillis())
        return notes.last()
    }

    fun delete(noteId: Int): Boolean {
        for (note: Note in deletedNotes) {
            if (noteId == note.id) {
                throw NoteNotFoundException("Заметка с таким id уже удалена")
            }
        }
        for (note: Note in notes) {
            if (noteId == note.id) {
                deletedNotes.add(note)
                notes.remove(note)
                return true
            }
        }
        return false
    }

    fun deleteComment(commentId: Int): Boolean {
        for (note: Note in notes) {
            for (comm: Comment in note.deletedComments) {
                if (commentId == comm.id) {
                    return throw CommentNotFoundException("Комментарий с таким id уже удален!")
                }
            }
            for (comm: Comment in note.comments) {
                if (commentId == comm.id) {
                    val tempCommentList = note.comments
                    tempCommentList.remove(comm)
                    val tempDeletedCommentList = note.deletedComments
                    tempDeletedCommentList.add(comm)
                    notes[notes.indexOf(note)] = note.copy(
                        comments = tempCommentList,
                        deletedComments = tempDeletedCommentList
                    )
                    return true
                }
            }
        }
        return false
    }


    fun createComment(comment: Comment): Boolean {

        for  (note: Note in notes) {
            if (note.id == comment.noteId) {
                note.comments.add(comment.copy(id = commentId++, date = System.currentTimeMillis()))
                return true
            }
        }
        return throw NoteNotFoundException("Заметки с таким id не существует!")
    }

    fun edit(input: Note): Boolean {
        for (note: Note in deletedNotes) {
            if (input.id == note.id) {

                return throw NoteNotFoundException("Данная заметка удалена!")
            }
        }


        for (note: Note in notes) {

            val index = notes.indexOf(note)
            if (note.id == input.id) {
                notes[index] = input.copy(
                    id = note.id,
                    comments = note.comments,
                    deletedComments = note.deletedComments,
                    date = System.currentTimeMillis(),
                    ownerId = note.ownerId
                )
                return true
            }
        }
        return throw NoteNotFoundException("Заметки с данным id не существует!")
    }

    fun editComment(comment: Comment): Boolean {
        for (note: Note in deletedNotes) {
            for (comm: Comment in note.comments) {
                if (comment.id == comm.id) {
                    return throw CommentNotFoundException("Комментарий удален вместе с заметкой!")
                }
            }
        }
        for (note: Note in notes) {
            for (comm: Comment in note.deletedComments) {
                if (comment.id == comm.id) {
                    return throw CommentNotFoundException("Комментарий был удален!")
                }
            }
            val tempCommentList = note.comments
            for (comm: Comment in note.comments) {
                if (comment.id == comm.id) {
                    tempCommentList[note.comments.indexOf(comm)] = comment.copy(
                        id = comm.id,
                        noteId = comm.noteId,
                        date = System.currentTimeMillis()
                    )
                    notes[notes.indexOf(note)] = note.copy(comments = tempCommentList)
                    return true
                }
            }
        }
        return throw CommentNotFoundException("Комментарий с таким id не найден!")
    }

    fun printNotes() {
        val notes = get()
        for (note in notes) {
            val dataFormat = SimpleDateFormat("dd:MM:yy HH:mm")
            val data = dataFormat.format(note.date)
            println("${note.title}\n${note.text}\n Id автора: ${note.ownerId}\n Id заметки: ${note.id}\n $data\n ")
            if (note.comments.isNotEmpty()) {
                println("Комментарии:")
                for (comment in note.comments) {
                    val dataComment = dataFormat.format(comment.date)
                    println("Комментарий № ${comment.id}\n ${comment.message}\n Дата: $dataComment\n")
                }
            }
        }
    }

    fun getById(noteId: Int) {
        for (note: Note in deletedNotes) {
            if (note.id == noteId) {
                return throw NoteNotFoundException("Заметка была удалена!")
            }
        }
        for (note: Note in notes) {
            if (noteId <= 0 || noteId > notes.size) {
                throw NoteNotFoundException("Заметка с таким id не существует!")
            }
            if (note.id == noteId) {
                val dataFormat = SimpleDateFormat("dd:MM:yy HH:mm")
                val data = dataFormat.format(note.date)
                println("${note.title}\n${note.text}\n Id автора: ${note.ownerId}\n Id заметки: ${note.id}\n $data\n ")
                if (note.comments.isNotEmpty()) {
                    println("Комментарии:")
                    for (comment in note.comments) {
                        val dataComment = dataFormat.format(comment.date)
                        println("Комментарий № ${comment.id}\n ${comment.message}\n Дата: $dataComment\n")
                    }
                }
            }
        }
    }

    fun getComments(noteId: Int) {
        for (note: Note in deletedNotes) {
            if (note.id == noteId) {
                return throw NoteNotFoundException("Заметка была удалена!")
            }
        }
        for (note: Note in notes) {
            if (noteId <= 0 || noteId > notes.size) {
                throw NoteNotFoundException("Заметка с таким id не существует!")
            }
            val dataFormat = SimpleDateFormat("dd:MM:yy HH:mm")
            val data = dataFormat.format(note.date)
            if (note.id == noteId && note.comments.isNotEmpty()) {
                println("Комментарии:")
                for (comment in note.comments) {
                    val dataComment = dataFormat.format(comment.date)
                    println("Комментарий № ${comment.id}\n ${comment.message}\n Дата: $dataComment\n")
                }
            }
        }
    }

    fun restoreComment(commentId: Int): Boolean {
        for (note: Note in deletedNotes) {
            for (comm: Comment in note.comments) {
                if (commentId == comm.id) {
                    return throw CommentNotFoundException("Коментарий удален вместе с заметкой!")
                }
            }
        }
        for (n: Note in notes) {
            for (comm: Comment in n.comments) {
                if (commentId == comm.id) {
                    return throw CommentNotFoundException("Комментарий с таким id уже восстановлен!")
                }
            }
            for (comm: Comment in n.deletedComments) {
                if (commentId == comm.id) {
                    val tempCommentList = n.comments
                    tempCommentList.add(comm)
                    val tempDeletedCommentList = n.deletedComments
                    tempDeletedCommentList.remove(comm)
                    notes[notes.indexOf(n)] = n.copy(
                        comments = tempCommentList,
                        deletedComments = tempDeletedCommentList
                    )
                    return true
                }
            }
        }
        return throw CommentNotFoundException("Комментарий с таким id не найден!")
    }
}
