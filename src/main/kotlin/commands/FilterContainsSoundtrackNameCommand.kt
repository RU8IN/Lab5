package commands

import storage.HumanCollectionInterface
import utils.PrintTypesEnum
import utils.CommandAnnotation

@kotlinx.serialization.Serializable
@CommandAnnotation("filter_contains_soundtrack_name", "Filters collection by soundtrack name")
class FilterContainsSoundtrackNameCommand(private val soundtrackName: String) : SealedCommand{

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        collection.filterContainsSoundtrackName(soundtrackName)
        return listOf(Pair(PrintTypesEnum.INFO, "Collection filtered"))
    }
}