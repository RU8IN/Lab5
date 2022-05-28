package commands

import storage.AbstractHumanCollection
import utils.CommandAnnotation
import utils.PrintTypesEnum

@CommandAnnotation("delete", "This command Deletes")
class DeleteCommand(private val id: Long) : SealedCommand {

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        collection.removeById(id)
        return mutableListOf(Pair(PrintTypesEnum.INFO, "Object with id $id deleted successfully!"))
    }
}