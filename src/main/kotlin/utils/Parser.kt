package utils

import commands.*
import exceptions.NoSuchCommandException

class Parser(private val logger: Logger) : ParserInterface {
    override fun parse(string: String): SealedCommand {

        when (string) {
            "add" -> return AddCommand.serializer().deserialize(ConsoleDecoder(logger))
            "add_if_max" -> return AddIfMaxCommand.serializer().deserialize(ConsoleDecoder(logger))
            "clear" -> return ClearCommand.serializer().deserialize(ConsoleDecoder(logger))
            "execute_script" -> return ExecuteScriptCommand.serializer().deserialize(ConsoleDecoder(logger))
            "exit" -> return ExitCommand.serializer().deserialize(ConsoleDecoder(logger))
            "filter_contains_soundtrack_name" -> return FilterContainsSoundtrackNameCommand.serializer().deserialize(ConsoleDecoder(logger))
            "help" -> return HelpCommand.serializer().deserialize(ConsoleDecoder(logger))
            "info" -> return InfoCommand.serializer().deserialize(ConsoleDecoder(logger))
            "remove_lower_by_impact_seed" -> RemoveAnyByImpactSeedCommand.serializer().deserialize(ConsoleDecoder(logger))
            "remove_by_id" -> return RemoveByIdCommand.serializer().deserialize(ConsoleDecoder(logger))
            "remove_first" -> return RemoveFirstCommand.serializer().deserialize(ConsoleDecoder(logger))
            "remove_lower" -> return RemoveLowerCommand.serializer().deserialize(ConsoleDecoder(logger))
            "save" -> return SaveCommand.serializer().deserialize(ConsoleDecoder(logger))
            "show" -> return ShowCommand.serializer().deserialize(ConsoleDecoder(logger))
            "sorted_by_descending_mood" -> return SortedByDescendingMood.serializer().deserialize(ConsoleDecoder(logger))
            "update" -> return UpdateCommand.serializer().deserialize(ConsoleDecoder(logger))
        }
        throw NoSuchCommandException()
    }
}