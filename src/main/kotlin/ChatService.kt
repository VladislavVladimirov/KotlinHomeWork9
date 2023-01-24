import data.Chat
import data.Message
import java.text.SimpleDateFormat

object ChatService {
    private var chats = mutableListOf<Chat>()

    private var id = 1

    fun get(): MutableList<Chat> {
        return chats
    }

    fun createChat(userId1: Int, userId2: Int): Chat {
        chats += Chat(id = id++, userId1 = userId1, userId2 = userId2)
        return chats.last()
    }

    fun deleteChat(userId: Int, chatId: Int): Boolean {
        val chat = findById(chatId) ?: return false
        if (chat.userId1 != userId && chat.userId2 != userId) {
            return false
        }
        chat.isDeleted = true
        return true
    }

    fun clear() {
        id = 1
        chats = mutableListOf()
    }

    fun addMessage(userId: Int, receiverId: Int, chatId: Int, text: String): Message {
        var chat = findById(chatId)
        if (chat == null) {
            chat = createChat(userId, receiverId)
        }
        val chatMessages = chat.messages
        val message = Message(
            id = chatMessages.size + 1,
            chatId = chatId,
            userId = userId,
            receiverId = receiverId,
            text = text,
            date = System.currentTimeMillis(),
            isUnread = true
        )
        chatMessages += message
        return message
    }

    fun printChats() {
        println("\nВсе чаты:")
        get().filter(fun(chat: Chat) = !chat.isDeleted)
            .forEach {
                val dataFormat = SimpleDateFormat("dd:MM:yy HH:mm")
                println("=========================================================")
                println("Чат ${it.id} пользователя ${it.userId1} и пользователя ${it.userId2}\n ")
                if (it.messages.isNotEmpty()) {
                    println("Сообщения чата:")
                    it.messages.forEach {
                        val dataOutput = dataFormat.format(it.date)
                        if (it.isUnread && !it.isDeleted) {
                            println("Сообщение  От пользователя ${it.userId}\n Дата: $dataOutput\n ${it.text}\n Сообщение непрочитано \n Id: ${it.id}\n")
                        }
                        if (!it.isUnread && !it.isDeleted) {
                            println("Сообщение  От пользователя ${it.userId}\n  Дата: $dataOutput\n ${it.text}\n Сообщение прочитано \n Id: ${it.id}\n")
                        }
                    }
                }
            }


    }

    fun deleteMessage(userId: Int, chatId: Int, messageId: Int): Boolean {
        val chat = findById(chatId) ?: return false
        if (chat.userId1 != userId && chat.userId2 != userId) {
            return false
        }
        try {
            val messageToDelete = chat.messages.first { it.id == messageId && !it.isDeleted }
            messageToDelete.isDeleted = true
        } catch (e: Exception) {
            return false
        }
        if (chat.messages.none { !it.isDeleted }) {
            deleteChat(userId, chatId)
        }
        return true
    }

    fun editMessage(userId: Int, chatId: Int, messageId: Int, text: String): Boolean {
        val chat = findById(chatId) ?: return false
        if (chat.userId1 != userId && chat.userId2 != userId) {
            return false
        }
        return try {
            val message = chat.messages.first { it.id == messageId && !it.isDeleted }
            message.text = text
            true
        } catch (e: NoSuchElementException) {
            false
        }
    }

    fun readMessages(userId: Int, chatId: Int, lastMessageId: Int, amount: Int): Boolean {
        val chat = findById(chatId) ?: return false
        if (chat.userId1 != userId && chat.userId2 != userId) {
            return false
        }
        if (chat != null) {
            val messagesIndices: MutableList<Int> = mutableListOf()
            val dataFormat = SimpleDateFormat("dd:MM:yy HH:mm")
            println("=========================================================")
            println("Cообщения из чата $chatId по вашему запросу:")
            chat.messages.asSequence()
                .filter(fun(message: Message) =
                    (message.id >= lastMessageId) && (message.id <= lastMessageId + amount))
                .filter(fun(message: Message) = message.userId == userId).filter(fun(message: Message) =
                    !message.isDeleted).forEach {
                    val dataOutput = dataFormat.format(it.date)
                    messagesIndices.add(it.id)
                    println("Сообщение  От пользователя ${it.userId}\n  Дата: $dataOutput\n ${it.text}\n  Id: ${it.id}\n")
                }
            chats[chats.indexOf(chat)].messages.forEach {
                if (messagesIndices.contains(it.id))
                    it.isUnread = false
            }


            println("=========================================================")
            return true
        }
        return false
    }

    fun getUnreadChatsCount(userId: Int): Int {
        var count = 0
        get().asSequence()
            .forEach {
                val unreadMessages = it.messages.asSequence()
                    .filter(fun(message: Message) = message.isUnread)
                    .filter(fun(message: Message) = message.receiverId == userId)
                    .toList()
                if (unreadMessages.isNotEmpty()) {
                    count++
                }
            }
        return count
    }

    fun getChats(userId: Int) {
        val unreadCount = getUnreadChatsCount(userId)
        if (unreadCount == 0) {
            println("Новых сообщений нет\n")
        }
        println("Список чатов для пользователя $userId с непрочитанными сообщениями:")
        get().asSequence()
            .filter(fun(chat: Chat) = !chat.isDeleted)
            .forEach {
                val unreadMessages = it.messages.asSequence()
                    .filter(fun(message: Message) = message.isUnread)
                    .filter(fun(message: Message) = message.receiverId == userId)
                    .toList()
                if (unreadMessages.isNotEmpty()) {
                    println("Чат ${it.id} пользователя ${it.userId1} и пользователя ${it.userId2}\n ")
                    val dataFormat = SimpleDateFormat("dd:MM:yy HH:mm")
                    val dataOutput = dataFormat.format(unreadMessages.last().date)
                    println("Последнее сообщение чата:\nСообщение  От пользователя ${unreadMessages.last().userId}\n Дата: $dataOutput\n ${unreadMessages.last().text}\n Id: ${unreadMessages.last().id}\n")
                }
            }
    }

    fun printUnreadChatsCount(userId: Int) {
        println("Число чатов пользователя $userId с непрочитанными сообщениями: ${getUnreadChatsCount(userId)}\n")
    }

    fun findById(chatId: Int): Chat? {
        return try {
            get().first { it.id == chatId && !it.isDeleted }
        } catch (e: Exception) {
            null
        }
    }

}











