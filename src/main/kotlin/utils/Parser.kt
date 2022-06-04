package utils

import commands.*
import exceptions.NoSuchCommandException

class Parser(private val logger: Logger) : ParserInterface {
    override fun parse(string: String): SealedCommand {

        when (string) {
            AddCommand::class.commandName, AddCommand::class.shortName -> return AddCommand.serializer().deserialize(ConsoleDecoder(logger))
            AddIfMaxCommand::class.commandName, AddIfMaxCommand::class.shortName -> return AddIfMaxCommand.serializer().deserialize(ConsoleDecoder(logger))
            ClearCommand::class.commandName, ClearCommand::class.shortName -> return ClearCommand.serializer().deserialize(ConsoleDecoder(logger))
            ExecuteScriptCommand::class.commandName, ExecuteScriptCommand::class.shortName-> return ExecuteScriptCommand.serializer().deserialize(ConsoleDecoder(logger))
            ExitCommand::class.commandName, ExitCommand::class.shortName -> return ExitCommand.serializer().deserialize(ConsoleDecoder(logger))
            FilterContainsSoundtrackNameCommand::class.commandName, FilterContainsSoundtrackNameCommand::class.shortName -> return FilterContainsSoundtrackNameCommand.serializer().deserialize(ConsoleDecoder(logger))
            HelpCommand::class.commandName, HelpCommand::class.shortName -> return HelpCommand.serializer().deserialize(ConsoleDecoder(logger))
            InfoCommand::class.commandName, InfoCommand::class.shortName -> return InfoCommand.serializer().deserialize(ConsoleDecoder(logger))
            RemoveAnyByImpactSeedCommand::class.commandName, RemoveAnyByImpactSeedCommand::class.shortName -> RemoveAnyByImpactSeedCommand.serializer().deserialize(ConsoleDecoder(logger))
            RemoveByIdCommand::class.commandName, RemoveByIdCommand::class.shortName -> return RemoveByIdCommand.serializer().deserialize(ConsoleDecoder(logger))
            RemoveFirstCommand::class.commandName, RemoveFirstCommand::class.shortName -> return RemoveFirstCommand.serializer().deserialize(ConsoleDecoder(logger))
            RemoveLowerCommand::class.commandName, RemoveLowerCommand::class.shortName -> return RemoveLowerCommand.serializer().deserialize(ConsoleDecoder(logger))
            SaveCommand::class.commandName, SaveCommand::class.shortName -> return SaveCommand.serializer().deserialize(ConsoleDecoder(logger))
            ShowCommand::class.commandName, ShowCommand::class.shortName -> return ShowCommand.serializer().deserialize(ConsoleDecoder(logger))
            SortedByDescendingMoodCommand::class.commandName, SortedByDescendingMoodCommand::class.shortName -> return SortedByDescendingMoodCommand.serializer().deserialize(ConsoleDecoder(logger))
            UpdateCommand::class.commandName, UpdateCommand::class.shortName -> return UpdateCommand.serializer().deserialize(ConsoleDecoder(logger))
        }
        throw NoSuchCommandException()
    }
}