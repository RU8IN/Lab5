package commands

import storage.AbstractHumanCollection
import utils.PrintTypesEnum
import utils.CommandAnnotation


@CommandAnnotation("filter_contains_soundtrack_name", "Filters collection by soundtrack name")
class FilterContainsSoundtrackNameCommand(private val soundtrackName: String) : SealedCommand{

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        collection.filterContainsSoundtrackName(soundtrackName)
        return listOf(Pair(PrintTypesEnum.INFO, "Collection filtered"))
    }
}