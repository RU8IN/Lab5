package commands

import storage.HumanCollectionInterface
import utils.PrintTypesEnum
import utils.CommandAnnotation

@kotlinx.serialization.Serializable
@CommandAnnotation("help", "This command helps you with understanding other programs!")
class HelpCommand() : SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        val commandsHelpList = mutableListOf<Pair<PrintTypesEnum, String>>()
        for (command in SealedCommand::class.sealedSubclasses) {
            val type = PrintTypesEnum.INFO
            val string = "${command.commandName} - ${command.commandHelp}"
            commandsHelpList.add(Pair(type, string))
        }
        return commandsHelpList
    }
}