package exception

class MessageNotFoundException(message: String): NoSuchElementException(message) {
}
