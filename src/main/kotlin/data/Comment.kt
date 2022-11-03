package data

data class Comment(
    val id: Int = 0,
    val noteId: Int = 0,
    val date: Long = 0,
    val message: String = ""
) {
    override fun toString(): String {
        return "id= $id, noteId= $noteId, date= $date ,text= $message"
    }
}