package commands

import driver.HumanBeing
import storage.AbstractHumanCollection
import utils.PrintTypesEnum
import utils.CommandAnnotation


@CommandAnnotation("update", "Updates element of collection by id")
class UpdateCommand(private val human: HumanBeing, private val id: Long) : SealedCommand {

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        collection.update(id, humanBeing = human)
        return mutableListOf(Pair(PrintTypesEnum.INFO, "Human with ID=$id updated"))
    }
}