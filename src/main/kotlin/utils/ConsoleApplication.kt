package utils

import com.sun.jmx.remote.internal.ArrayQueue
import commands.HelpCommand
import commands.SealedCommand
import storage.AbstractHumanCollection
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.properties.Delegates


class ConsoleApplication(
    private val parser: ParserInterface,
    private val logger: Logger,
    private val collection: AbstractHumanCollection
) {

    private var lastExecutedCommands = ArrayQueue<SealedCommand>(10)

    fun run() {
        var currentCommand: SealedCommand by Delegates.observable(HelpCommand()) { _, _, new ->
            lastExecutedCommands.add(new)
        }
        while (true) {
            currentCommand = parser.parse(readln())
            logger.log(currentCommand.execute(collection))
        }
    }

    fun getLastCommands(): List<SealedCommand> {
        return lastExecutedCommands
    }


}