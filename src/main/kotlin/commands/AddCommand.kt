package commands

import driver.HumanBeing
import kotlinx.serialization.Serializable
import storage.HumanCollectionInterface
import utils.CommandAnnotation
import utils.PrintTypesEnum

@Serializable
@CommandAnnotation("add", "This command adds object to deque", "ad")
class AddCommand(private val humanBeing: HumanBeing) : SealedCommand() {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        collection.add(humanBeing)
        val list = mutableListOf<Pair<PrintTypesEnum, String>>()
        list.add(Pair(PrintTypesEnum.INFO, "Human ${humanBeing.name} has been added successfully!"))
        return list
    }
}