package commands

import exceptions.NoAnnotationError
import storage.HumanCollectionInterface
import utils.CommandAnnotation
import utils.PrintTypesEnum
import kotlin.reflect.KClass

sealed interface SealedCommand{

    abstract fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>>

}

val SealedCommand.name: String
    get() = this::class.commandName
val SealedCommand.help: String
    get() = this::class.commandHelp
val SealedCommand.shortName: String
    get() = this::class.shortName

val <T: SealedCommand> KClass<T>.commandName: String
get() = this.java.getAnnotation(CommandAnnotation::class.java)?.name ?: throw NoAnnotationError()

val <T: SealedCommand> KClass<T>.commandHelp: String
    get() = this.java.getAnnotation(CommandAnnotation::class.java)?.help ?: throw NoAnnotationError()

val <T: SealedCommand> KClass<T>.shortName: String
    get() = this.java.getAnnotation(CommandAnnotation::class.java)?.shortName ?: throw NoAnnotationError()
