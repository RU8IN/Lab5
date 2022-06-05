package commands

import storage.HumanCollectionInterface
import utils.CommandAnnotation
import utils.PrintTypesEnum
import java.io.*

@kotlinx.serialization.Serializable
@CommandAnnotation("execute_script", "Executes script with commands from file", "es")
class ExecuteScriptCommand() : SealedCommand {
    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
//        val sb = StringBuilder()
//        var line: String? = ""
//        try {
//            val br: BufferedReader = BufferedReader(FileReader(File(filePath)))
//            br.use {
//                while (line != null) {
//                    line = br.readLine()
//                    if (line != null) {
//                        sb.append(line).append("\n")
//                    }
//                }
//            }
//        } catch (e: FileNotFoundException) {
//            println("File not found: $filePath")
//        } catch (e: IOException) {
//            println("File could not be read: $filePath")
//        }
//        return listOf(PrintTypesEnum.INFO to "Script executed")
//    }
        return listOf(PrintTypesEnum.WARNING to "TODO: EXECUTE SCRIPT") }
}

