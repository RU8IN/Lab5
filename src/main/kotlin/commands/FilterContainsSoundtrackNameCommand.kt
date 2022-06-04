package commands

import driver.HumanBeing
import storage.HumanCollectionInterface
import utils.PrintTypesEnum
import utils.CommandAnnotation

@kotlinx.serialization.Serializable
@CommandAnnotation("filter_contains_soundtrack_name", "Filters collection by soundtrack name", "fcsn")
class FilterContainsSoundtrackNameCommand(private val soundtrackName: String) : SealedCommand{

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        val list = mutableListOf(Pair(PrintTypesEnum.INFO, "Collection filtered"))
        for (humanBeing in collection.filterContainsSoundtrackName(soundtrackName)) {
            list.add(PrintTypesEnum.INFO to humanBeing.toString())
        }
        return list
    }
}