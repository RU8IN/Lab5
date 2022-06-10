package commands

import exceptions.ExecuteScriptRecursionException
import exceptions.SaveException
import storage.HumanCollectionInterface
import utils.CommandAnnotation
import utils.ConsoleLogger
import utils.Parser
import utils.PrintTypesEnum
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.lang.Integer.max


@kotlinx.serialization.Serializable
@CommandAnnotation("execute_script", "Executes script with commands from file", "es")
class ExecuteScriptCommand(val pathName: String) : SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        var recursionCounter = 0
        val newRecursionCounter: Int
        val list = listOf<Pair<PrintTypesEnum, String>>()
        val logger = ConsoleLogger
        val parser = Parser(logger)

        if (File("execute.meta").exists()) {
            try {
                val bufferedReader = BufferedReader(File("execute.meta").bufferedReader())
                bufferedReader.use {
                    newRecursionCounter = bufferedReader.readLine().toInt()
                    recursionCounter = max(recursionCounter, newRecursionCounter)
                }
            } catch (e: Exception) {
                logger.log(PrintTypesEnum.WARNING to e.message.toString())
            }
        }

        val standardIn = System.`in`
        File(pathName).inputStream().use {
            System.setIn(it)

            while (true) {
                try {
                    val currentCommand = parser.parse(readln())
                    if (currentCommand.name == ExecuteScriptCommand::class.commandName && recursionCounter > 5) {
                        val file = File("execute.meta")
                        file.delete()
                        throw ExecuteScriptRecursionException()
                    } else if (currentCommand.name == ExecuteScriptCommand::class.commandName) {
                        recursionCounter += 1
                        val bufferedWriter = BufferedWriter(File("execute.meta").bufferedWriter())
                        try {
                            bufferedWriter.use {
                                bufferedWriter.write(
                                    recursionCounter.toString()
                                )
                            }
                        } catch (e: RuntimeException) {
                            throw SaveException()
                        }
                    }
                    logger.log(currentCommand.execute(collection))
                } catch (e: ExecuteScriptRecursionException) {
                    System.setIn(standardIn)
                    return listOf(PrintTypesEnum.WARNING to e.message.toString())
                } catch (e: RuntimeException) {
                    break
                }
            }
            System.setIn(standardIn)
        }
        if (recursionCounter == 1) {
            return listOf(PrintTypesEnum.INFO to "Script executed successfully")
        } else {
            return list
        }
    }
}

