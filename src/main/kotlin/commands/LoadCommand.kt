package commands

import exceptions.SaveException
import storage.HumanCollectionInterface
import utils.CommandAnnotation
import utils.PrintTypesEnum
import java.io.BufferedReader
import java.io.File

@kotlinx.serialization.Serializable
@CommandAnnotation("load", "Loads collection from xml file")
class LoadCommand(val filePath: String) : SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {

        val bufferedReader = BufferedReader(File(filePath).bufferedReader())
        var xmlCollection = ""
        try {
            bufferedReader.use {
                xmlCollection += bufferedReader.readLine()
            }
        }
        catch (e: RuntimeException) {
            return listOf(PrintTypesEnum.WARNING to "Something is wrong with the file")
        }

        return listOf(PrintTypesEnum.WARNING to "Something is wrong with the file")
    }
}