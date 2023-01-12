package data

data class Chat(
    val id: Int = 0,
    val userId1: Int = 0,
    val userId2: Int = 0,
    val messages: MutableList<Message> = mutableListOf(),
    var isDeleted: Boolean = false
) {
    override fun toString(): String {
        return "id= $id, user 1 = $userId1,  messages = $messages)"

    }
}