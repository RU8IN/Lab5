package commands

import storage.HumanCollectionInterface
import utils.PrintTypesEnum
import utils.CommandAnnotation

@kotlinx.serialization.Serializable
@CommandAnnotation("remove_first", "Removes first element in collection", "rf")
class RemoveFirstCommand() : SealedCommand() {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        collection.removeFirst()
        return listOf(Pair(PrintTypesEnum.INFO, "First element removed"))
    }
}