package commands

import kotlinx.serialization.Serializable
import storage.HumanCollectionInterface
import utils.PrintTypesEnum
import utils.CommandAnnotation

@Serializable
@CommandAnnotation("clear", "Clears collection")
class ClearCommand : SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        collection.clear()
        return mutableListOf(Pair(PrintTypesEnum.INFO, "Collection cleared"))
    }
}