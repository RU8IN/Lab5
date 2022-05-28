package commands

import driver.HumanBeing
import storage.AbstractHumanCollection
import utils.PrintTypesEnum
import utils.CommandAnnotation


@CommandAnnotation("add_if_max", "Adds element if it is higher than max in collection")
class AddIfMaxCommand(private val human: HumanBeing) : SealedCommand {

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        if (collection.addIfMax(human))
            return listOf(Pair(PrintTypesEnum.INFO, "Element added"))
        else {
            return listOf(Pair(PrintTypesEnum.INFO, "Element not added"))
        }
    }
}