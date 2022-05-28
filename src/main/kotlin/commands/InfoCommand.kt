package commands

import storage.AbstractHumanCollection
import utils.PrintTypesEnum
import utils.CommandAnnotation
import kotlin.reflect.full.declaredMemberProperties


@CommandAnnotation("info", "This command shows the type, initialization date and size of collection")
class InfoCommand() : SealedCommand {

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        return listOf(Pair(PrintTypesEnum.INFO, collection.getInfo()))
    }
}