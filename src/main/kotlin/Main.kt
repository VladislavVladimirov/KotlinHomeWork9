import ChatService.addMessage
import ChatService.createChat
import ChatService.deleteChat
import ChatService.deleteMessage
import ChatService.editMessage
import ChatService.findById
import ChatService.getChats
import ChatService.getUnreadChatsCount
import ChatService.printChats
import ChatService.printUnreadChatsCount
import ChatService.readMessages
import data.Chat

fun main() {
    createChat(1, 2)

    addMessage(1, 2, 1, "Привет")
    addMessage(2, 1, 1, "Здравствуй!")

    addMessage(2, 3, 2, "Как дела?")
    addMessage(3, 2, 2, "Всё отлично")
    addMessage(3, 2, 2, "Чем занимаешься?")
    addMessage(2, 3, 2, "Я программирую, а ты?")
    addMessage(2, 3, 2, "Прохожу новую тему - это интересно")

    addMessage(3, 4, 3, "Новый чат")
    addMessage(3, 5, 4, "Ещё один новый чат")
    deleteMessage(3, 4, 1)



    editMessage(2, 2, 1, "Как дела? Что нового?")
    readMessages(2, 2, 1, 2)


    deleteChat(3, 3)


    printUnreadChatsCount(1)
    getChats(1)

    printChats()
}