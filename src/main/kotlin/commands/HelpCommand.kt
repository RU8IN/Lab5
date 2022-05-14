package commands

import utils.PrintTypesEnum


class HelpCommand(private vararg val commands: SealedCommand) : SealedCommand() {


    override val name = "help"
    override val help = "This command helps you with understanding other programs!"

    override fun execute(): List<Pair<PrintTypesEnum, String>> {
        val commandsList = mutableListOf<Pair<PrintTypesEnum, String>>()
        for (command in commands) {
            val type = PrintTypesEnum.INFO
            val helpString = command.help

            commandsList.add(Pair(type, helpString))
        }
        return commandsList
    }
}