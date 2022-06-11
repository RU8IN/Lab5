package commands

import driver.HumanBeing
import storage.HumanCollectionInterface
import utils.ANSIColors
import utils.CommandAnnotation
import utils.PrintTypesEnum

@kotlinx.serialization.Serializable
@CommandAnnotation("update", "Updates element of collection by id", "up")
class UpdateCommand(private val human: HumanBeing, private val id: Long) : SealedCommand() {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        collection.update(id, humanBeing = human)
        return mutableListOf(
            Pair(
                PrintTypesEnum.INFO,
                "Human with ID=${ANSIColors.CYAN}$id${ANSIColors.RESET} updated"
            )
        )
    }
}