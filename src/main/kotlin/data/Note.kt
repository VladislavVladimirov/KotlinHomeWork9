package data

data class Note(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val text: String = "",
    val date: Long = 0,
    val comments: MutableList<Comment> = mutableListOf(),
    val deletedComments: MutableList<Comment> = mutableListOf()

) {
    override fun toString(): String {
        return "id= $id, ownerId= $ownerId, title= $title, text= $text, date= $date, comments= $comments," +
                "deleted comments = $deletedComments"
    }
}