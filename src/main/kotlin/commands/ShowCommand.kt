package commands

import storage.AbstractHumanCollection
import utils.PrintTypesEnum
import utils.CommandAnnotation


@CommandAnnotation("show", "This shows every element in collection")
class ShowCommand() : SealedCommand {

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        val elementsList = mutableListOf<Pair<PrintTypesEnum, String>>()

        collection.forEach {
            elementsList.add(Pair(PrintTypesEnum.INFO, it.toString()))
        }

        return elementsList
    }
}