package commands

import driver.HumanBeing
import storage.HumanCollectionInterface
import utils.PrintTypesEnum
import utils.CommandAnnotation

@kotlinx.serialization.Serializable
@CommandAnnotation("update", "Updates element of collection by id")
class UpdateCommand(private val human: HumanBeing, private val id: Long) : SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        collection.update(id, humanBeing = human)
        return mutableListOf(Pair(PrintTypesEnum.INFO, "Human with ID=$id updated"))
    }
}