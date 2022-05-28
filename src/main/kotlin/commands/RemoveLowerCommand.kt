package commands

import driver.HumanBeing
import storage.AbstractHumanCollection
import utils.PrintTypesEnum
import utils.CommandAnnotation


@CommandAnnotation("remove_first", "Removes first element in collection")
class RemoveLowerCommand(private val human: HumanBeing) : SealedCommand {

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        collection.removeLower(human)
        return listOf(Pair(PrintTypesEnum.INFO, "Elements removed"))
    }
}