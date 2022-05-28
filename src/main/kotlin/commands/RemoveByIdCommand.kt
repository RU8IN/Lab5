package commands

import driver.HumanBeing
import kotlinx.serialization.Serializable
import storage.AbstractHumanCollection
import utils.PrintTypesEnum
import utils.CommandAnnotation

@Serializable
@CommandAnnotation("remove_by_id", "Removes element by id")
class RemoveByIdCommand(private val id: Long) : SealedCommand {

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        collection.removeById(id)
        return mutableListOf(Pair(PrintTypesEnum.INFO, "Object with ID=$id removed"))
    }
}