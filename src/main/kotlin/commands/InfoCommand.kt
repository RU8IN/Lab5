package commands

import storage.HumanCollectionInterface
import utils.PrintTypesEnum
import utils.CommandAnnotation

@kotlinx.serialization.Serializable
@CommandAnnotation("info", "This command shows the type, initialization date and size of collection", "in")
class InfoCommand() : SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        return listOf(Pair(PrintTypesEnum.INFO, collection.getInfo()))
    }
}