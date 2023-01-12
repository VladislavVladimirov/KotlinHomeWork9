import data.Chat
import data.Message
import exception.ChatNotFoundException
import exception.MessageNotFoundException
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
        val chats = get().filter(fun(chat: Chat) = !chat.isDeleted)
        println("\nВсе чаты:")
        for (chat in chats) {
            val dataFormat = SimpleDateFormat("dd:MM:yy HH:mm")
            println("=========================================================")
            println("Чат ${chat.id} пользователя ${chat.userId1} и пользователя ${chat.userId2}\n ")
            if (chat.messages.isNotEmpty()) {
                println("Сообщения чата:")
                for (message in chat.messages) {
                    val dataOutput = dataFormat.format(message.date)
                    if (message.isUnread && !message.isDeleted) {
                        println("Сообщение  От пользователя ${message.userId}\n Дата: $dataOutput\n ${message.text}\n Сообщение непрочитано \n Id: ${message.id}\n")
                    }
                    if (!message.isUnread && !message.isDeleted) {
                        println("Сообщение  От пользователя ${message.userId}\n  Дата: $dataOutput\n ${message.text}\n Сообщение прочитано \n Id: ${message.id}\n")
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
        val chat = findById(chatId)
        if (chat != null) {
            for (message: Message in chat.messages) {
                if (message.id == messageId && message.isDeleted) {
                    return throw MessageNotFoundException("Сообщение удалено, невозможно отредактировать")
                }
            }
            if (chat.userId1 != userId && chat.userId2 != userId) {
                return false
            }
            for (message: Message in chat.messages) {
                if (messageId == message.id) {
                    message.text = text
                    return true
                }
            }
        }
        return false
    }

    fun readMessages(userId: Int, chatId: Int, lastMessageId: Int, amount: Int): Boolean {
        val chat = findById(chatId) ?: return false
        if (chat.userId1 != userId && chat.userId2 != userId) {
            return false
        }
        if (chat != null) {

            val output = chat.messages.filter(fun(message: Message) =
                (message.id >= lastMessageId) && (message.id <= lastMessageId + amount))
                .filter(fun(message: Message) = message.userId == userId).filter(fun(message: Message) =
                    !message.isDeleted)
            val messagesIndices: MutableList<Int> = mutableListOf()
            val dataFormat = SimpleDateFormat("dd:MM:yy HH:mm")
            println("=========================================================")
            println("Cообщения из чата $chatId по вашему запросу:")
            for (message: Message in output) {
                if (!message.isDeleted) {
                    val dataOutput = dataFormat.format(message.date)
                    messagesIndices.add(message.id)
                    println("Сообщение  От пользователя ${message.userId}\n  Дата: $dataOutput\n ${message.text}\n  Id: ${message.id}\n")
                }
            }
            for (index in messagesIndices) {
                for (message in chats[chats.indexOf(chat)].messages) {
                    if (messagesIndices.contains(message.id))
                        message.isUnread = false
                }

            }
            println("=========================================================")
            return true
        }
        return false
    }

    fun getUnreadChatsCount(userId: Int): Int {
        val chats = get()
        var count = 0
        for (chat: Chat in chats) {
            val unreadMessages = chat.messages.filter(fun(message: Message) = message.isUnread)
                .filter(fun(message: Message) = message.receiverId == userId)
            if (unreadMessages.isNotEmpty()) {
                count++
            }
        }
        return count
    }

    fun getChats(userId: Int) {
        val chats = get().filter(fun(chat: Chat) = !chat.isDeleted)
        val unreadCount = getUnreadChatsCount(userId)
        if (unreadCount == 0) {
            println("Новых сообщений нет\n")
        }
        println("Список чатов для пользователя $userId с непрочитанными сообщениями:")
        for (chat: Chat in chats) {
            val unreadMessages = chat.messages.filter(fun(message: Message) = message.isUnread)
                .filter(fun(message: Message) = message.receiverId == userId)
            val unreadIndices = unreadMessages.indices.toList()
            if (unreadMessages.isNotEmpty()) {
                println("Чат ${chat.id} пользователя ${chat.userId1} и пользователя ${chat.userId2}\n ")
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
        for (chat: Chat in chats) {
            if (chat.id == chatId && chat.isDeleted) {
                return throw ChatNotFoundException("Чат удален")
            }
        }
        for (chat: Chat in chats) {
            if (chat.id == chatId) {
                return chat
            }
        }
        return null
    }

}











