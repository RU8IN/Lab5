package commands

import exceptions.SaveException
import storage.HumanCollectionInterface
import utils.CommandAnnotation
import utils.PrintTypesEnum
import java.io.BufferedWriter
import java.io.File

@kotlinx.serialization.Serializable
@CommandAnnotation("save", "Saves current collection to file")
class SaveCommand(val filePath: String) : SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
//
//        val bufferedWriter = BufferedWriter(File(filePath).bufferedWriter())
//
//        try {
//            bufferedWriter.use {
//                bufferedWriter.write(collection.toXml())
//            }
            return listOf(PrintTypesEnum.INFO to "Collections saved")
//        }
//        catch (e: RuntimeException) {
//            throw SaveException()
//        }

    }
}