package commands

import driver.HumanBeing
import storage.HumanCollectionInterface
import utils.PrintTypesEnum
import utils.CommandAnnotation

@kotlinx.serialization.Serializable
@CommandAnnotation("remove_lower", "Removes minimal element in collection")
class RemoveLowerCommand(private val human: HumanBeing) : SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        collection.removeLower(human)
        return listOf(Pair(PrintTypesEnum.INFO, "Elements removed"))
    }
}