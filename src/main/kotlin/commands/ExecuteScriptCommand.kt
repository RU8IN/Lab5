package commands

import storage.HumanCollectionInterface
import utils.CommandAnnotation
import utils.PrintTypesEnum

@kotlinx.serialization.Serializable
@CommandAnnotation("execute_script", "Executes script with commands from file", "es")
class ExecuteScriptCommand(): SealedCommand {
    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        TODO("Not yet implemented")
    }
}