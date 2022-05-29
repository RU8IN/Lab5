package commands

import storage.HumanCollectionInterface
import utils.CommandAnnotation
import utils.ConsoleLogger
import utils.PrintTypesEnum
import kotlin.system.exitProcess

@kotlinx.serialization.Serializable
@CommandAnnotation("exit", "Exits")
class ExitCommand(): SealedCommand {
    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        val logger = ConsoleLogger
        logger.log(PrintTypesEnum.INFO to "Exiting program...")
        exitProcess(0)
    }
}