package commands

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
        try {
            collection.loadCollection(filePath)
        }
        catch (exception: FileNotFoundException) {
            return listOf(PrintTypesEnum.WARNING to "Can't find file")
        }
        if (collection.isEmpty()) return listOf(PrintTypesEnum.INFO to "Collection in file is empty!")
        return listOf(PrintTypesEnum.INFO to "Collection loaded!")
    }
}
//
//private val module = SerializersModule {
//    contextual(HumanBeingFileSerializer)
//}
//
//val format = Json { serializersModule = module }
