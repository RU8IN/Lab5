package commands

import exceptions.SaveException
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import storage.HumanCollectionInterface
import storage.LocalHumanCollection
import utils.CommandAnnotation
import utils.PrintTypesEnum
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException

@kotlinx.serialization.Serializable
@CommandAnnotation("load", "Loads collection from json file", "lo")
class LoadCommand(
    @SerialName("File Path")
    val filePath: String
    ): SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        val bufferedReader = BufferedReader(File(filePath).bufferedReader())
        var jsonCollection = ""
        try {
            bufferedReader.use {
                jsonCollection += bufferedReader.readLine()
            }
        }
        catch (exception: FileNotFoundException) {
            return listOf(PrintTypesEnum.WARNING to "Can't find file")
        }
        val newCollection = Json.decodeFromString(LocalHumanCollection.serializer(), jsonCollection)
        if (newCollection.isEmpty()) return listOf(PrintTypesEnum.WARNING to "Collection in file is empty!")
        collection.loadCollection(Json.decodeFromString(LocalHumanCollection.serializer(), jsonCollection))
        return listOf(PrintTypesEnum.INFO to "Collection loaded!")
    }

}