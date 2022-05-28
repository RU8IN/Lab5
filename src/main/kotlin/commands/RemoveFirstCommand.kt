package commands

import storage.AbstractHumanCollection
import utils.PrintTypesEnum
import utils.CommandAnnotation


@CommandAnnotation("remove_first", "Removes first element in collection")
class RemoveFirstCommand() : SealedCommand {

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        collection.removeFirst()
        return listOf(Pair(PrintTypesEnum.INFO, "First element removed"))
    }
}