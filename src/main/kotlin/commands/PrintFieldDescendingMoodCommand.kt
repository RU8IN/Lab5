package commands

import storage.AbstractHumanCollection
import utils.PrintTypesEnum
import utils.CommandAnnotation


@CommandAnnotation("print_field_descending_mood", "Prints collection sorted by descending mood")
class PrintFieldDescendingMoodCommand() : SealedCommand {

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        val elementsList = mutableListOf<Pair<PrintTypesEnum, String>>()
        collection.sortedByDescendingMood().forEach {
            elementsList.add(Pair(PrintTypesEnum.INFO, it.toString()))
        }
        return elementsList
    }
}