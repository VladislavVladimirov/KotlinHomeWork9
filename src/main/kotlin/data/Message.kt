package data

data class Message(
    val id: Int = 0,
    val chatId: Int = 0,
    val userId: Int = 0,
    val receiverId: Int = 0,
    var text: String = "",
    val date: Long = 0,
    var isUnread: Boolean = true,
    var isDeleted: Boolean = false
) {
    override fun toString(): String {
        return "id= $id, senderId = $userId, text = $text, date= $date, Unread= $isUnread"

    }
}