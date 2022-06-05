package commands

import exceptions.SaveException
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import storage.HumanCollectionInterface
import storage.LocalHumanCollection
import utils.CommandAnnotation
import utils.PrintTypesEnum
import java.io.BufferedWriter
import java.io.File

@kotlinx.serialization.Serializable
@CommandAnnotation("save", "Saves current collection to file", "sa")
class SaveCommand(
    @SerialName("File Path")
    val filePath: String
    ) : SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {

        val bufferedWriter = BufferedWriter(File(filePath).bufferedWriter())

        try {
            bufferedWriter.use {
                bufferedWriter.write((Json.encodeToString(LocalHumanCollection.serializer(), collection as LocalHumanCollection)))
            }
            return listOf(PrintTypesEnum.INFO to "Collections saved")
        }
        catch (e: RuntimeException) {
            throw SaveException()
        }

    }
}