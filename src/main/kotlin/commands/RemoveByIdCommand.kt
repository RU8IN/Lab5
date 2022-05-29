package commands

import storage.HumanCollectionInterface
import utils.PrintTypesEnum
import utils.CommandAnnotation

@kotlinx.serialization.Serializable
@CommandAnnotation("remove_by_id", "Removes element by id")
class RemoveByIdCommand(private val id: Long) : SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        collection.removeById(id)
        return mutableListOf(Pair(PrintTypesEnum.INFO, "Object with ID=$id removed"))
    }
}