import ChatService.readMessages
import data.Chat
import exception.ChatNotFoundException
import exception.MessageNotFoundException
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

internal class ChatServiceTest {
    @Before
    fun clearBeforeTest() {
        ChatService.clear()
    }

    @Test
    fun createChat() {
        ChatService.createChat(1, 2)
        val chats = ChatService.get()
        val result = chats.size
        assertEquals(1, result)
    }

    @Test
    fun deleteChatTrue() {
        val chat = ChatService.createChat(1, 2)
        val result = ChatService.deleteChat(1, chat.id)
        assertTrue(result)
    }

    @Test
    fun deleteChatFalseWrongUser() {
        val chat = ChatService.createChat(1, 2)
        val result = ChatService.deleteChat(3, chat.id)
        assertFalse(result)

    }

    @Test
    fun deleteChatFalseNoChat() {
        val result = ChatService.deleteChat(1, 0)
        assertFalse(result)

    }

    @Test
    fun addMessage() {
        val chat = ChatService.createChat(1, 2)
        ChatService.addMessage(1, 2, 1, "Hello")
        val result = chat.messages.isNotEmpty()
        assertTrue(result)
    }

    @Test
    fun addMessageCreateChat() {
        ChatService.addMessage(1, 2, 1, "Hello")
        val chats = ChatService.get()
        val result = chats.size
        assertEquals(1, result)
    }

    @Test
    fun deleteMessageTrue() {
        ChatService.addMessage(1, 1, 2, "Привет")
        ChatService.addMessage(1, 1, 2, "Как дела?")
        val result = ChatService.deleteMessage(1, 1, 1)
        assertTrue(result)
    }

    @Test
    fun deleteMessageNoMessage() {
        ChatService.addMessage(1, 2, 1, "Привет")
        ChatService.addMessage(1, 2, 1, "Как дела?")
        ChatService.deleteMessage(1, 1, 2)
        val res = ChatService.deleteMessage(1, 1, 2)
        assertFalse(res)

    }

    @Test
    fun deleteMessageTrueDeleteChat() {
        ChatService.addMessage(1, 1, 2, "Привет")
        val result = ChatService.deleteMessage(1, 1, 1)
        assertTrue(result)

    }

    @Test
    fun deleteMessageFalseWrongUser() {
        ChatService.addMessage(1, 1, 2, "Привет")
        val result = ChatService.deleteMessage(3, 1, 1)
        assertFalse(result)
    }

    @Test
    fun deleteMessageFalseWrongMessageId() {
        ChatService.addMessage(1, 1, 2, "Привет")
        val result = ChatService.deleteMessage(1, 1, 2)
        assertFalse(result)
    }

    @Test
    fun printChats() {
        ChatService.createChat(1, 2)
        ChatService.addMessage(1, 1, 2, "Привет")
        val result = ChatService.printChats().toString()
        assertNotEquals("", result)

    }

    @Test
    fun editMessageTrue() {
        ChatService.addMessage(1, 1, 1, "Привет")
        val result = ChatService.editMessage(1, 1, 1, "Изменено")
        assertTrue(result)
    }

    @Test
    fun editMessageFalseWrongMessageId() {
        ChatService.addMessage(1, 1, 1, "Привет")
        val result = ChatService.editMessage(1, 1, 2, "Изменено")
        assertFalse(result)
    }

    @Test
    fun editMessageFalseWrongUserId() {
        ChatService.addMessage(1, 1, 1, "Привет")
        val result = ChatService.editMessage(2, 1, 1, "Изменено")
        assertFalse(result)
    }

    @Test
    fun readMessagesTrue() {
        ChatService.createChat(1, 2)
        ChatService.addMessage(1, 1, 2, "Привет")
        val result = ChatService.readMessages(1, 1, 1, 1)
        assertTrue(result)
    }

    @Test
    fun readMessagesFalseWrongUserId() {
        ChatService.createChat(1, 2)
        ChatService.addMessage(1, 1, 2, "Привет")
        val result = ChatService.readMessages(3, 1, 1, 1)
        assertFalse(result)
    }

    @Test
    fun readMessagesFalseWrongChatId() {
        ChatService.createChat(1, 2)
        ChatService.addMessage(1, 1, 2, "Привет")
        val result = ChatService.readMessages(1, 3, 1, 1)
        assertFalse(result)
    }

    @Test
    fun getUnreadChatsCount() {
        ChatService.addMessage(1, 2, 1, "Привет")
        ChatService.addMessage(2, 1, 1, "Здравствуй!")
        ChatService.addMessage(2, 3, 2, "Как дела?")
        ChatService.addMessage(3, 2, 2, "Всё отлично")
        ChatService.addMessage(3, 2, 2, "Чем занимаешься?")
        ChatService.addMessage(2, 3, 2, "Я программирую, а ты?")
        ChatService.addMessage(2, 3, 2, "Прохожу новую тему - это интересно")
        ChatService.addMessage(3, 4, 3, "Новый чат")
        ChatService.addMessage(3, 5, 4, "Ещё один новый чат")
        ChatService.readMessages(2, 2, 1, 2)
        val result = ChatService.getUnreadChatsCount(1)
        assertEquals(1, result)
    }

    @Test
    fun getChats() {
        ChatService.addMessage(1, 2, 1, "Привет")
        ChatService.addMessage(2, 1, 1, "Здравствуй!")
        ChatService.addMessage(2, 3, 2, "Как дела?")
        ChatService.addMessage(3, 2, 2, "Всё отлично")
        ChatService.addMessage(3, 2, 2, "Чем занимаешься?")
        ChatService.addMessage(2, 3, 2, "Я программирую, а ты?")
        ChatService.addMessage(2, 3, 2, "Прохожу новую тему - это интересно")
        ChatService.addMessage(3, 4, 3, "Новый чат")
        ChatService.addMessage(3, 5, 4, "Ещё один новый чат")
        val result = ChatService.getChats(1).toString()
        assertNotEquals("", result)
    }


}