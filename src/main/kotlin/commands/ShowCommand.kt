package commands

import storage.HumanCollectionInterface
import utils.PrintTypesEnum
import utils.CommandAnnotation

@kotlinx.serialization.Serializable
@CommandAnnotation("show", "This shows every element in collection", "sh")
class ShowCommand() : SealedCommand() {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        val elementsList = mutableListOf<Pair<PrintTypesEnum, String>>()

        collection.forEach {
            elementsList.add(Pair(PrintTypesEnum.INFO, it.toString()))
        }
        if (elementsList.isEmpty()) return listOf(Pair(PrintTypesEnum.WARNING, "Collection is empty"))
        return elementsList
    }
}