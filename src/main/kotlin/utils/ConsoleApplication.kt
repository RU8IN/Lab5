package utils

import kotlin.properties.Delegates


class ConsoleApplication(
    private val parser: ParserInterface,
    private val logger: Logger
) {

    var currentString: String by Delegates.observable("init") { prop, old, new ->
        println("$old -> $new")
    }

    suspend fun run() {
        while (true) {
            currentString = readln()
        }
    }
}