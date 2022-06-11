package commands

import exceptions.NoAnnotationError
import storage.HumanCollectionInterface
import utils.CommandAnnotation
import utils.PrintTypesEnum
import java.io.*
import kotlin.reflect.KClass

sealed class SealedCommand: Serializable {
    abstract fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>>



    fun toByteArray(): ByteArray {
        val bos = ByteArrayOutputStream();
        try {
            val out = ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (e: Exception) {
                println(e.toString())
            }
        }
    }

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

fun fromByteArray(byteArray: ByteArray): SealedCommand {
    val bis = ByteArrayInputStream(byteArray);
    var inn: ObjectInputStream? = null;
    try {
        inn = ObjectInputStream(bis);
        val command = inn.readObject();
        return command as SealedCommand
    } finally {
        try {
            inn?.close()
        } catch (e: Exception) {
            println(e.toString())
        }
    }
}
