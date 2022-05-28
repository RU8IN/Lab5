package commands

import driver.HumanBeing
import kotlinx.serialization.Serializable
import storage.AbstractHumanCollection
import utils.PrintTypesEnum
import utils.CommandAnnotation

@Serializable
@CommandAnnotation("clear", "Clears collection")
object ClearCommand : SealedCommand {

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        collection.clear()
        return mutableListOf(Pair(PrintTypesEnum.INFO, "Collection cleared"))
    }
}