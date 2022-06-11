package utils
import commands.LoadCommand
import storage.HumanCollectionInterface
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream


class ConsoleApplication(
    private val parser: ParserInterface,
    private val logger: Logger,
    private val collection: HumanCollectionInterface,
    private val collectionPath: String? = null
) {

    fun run() {
        logger.log(PrintTypesEnum.INFO to "Ryan Gosling Maker 1.0")
        collectionPath?.let {
            LoadCommand(collectionPath).execute(collection)
        }

        while (true) {
            try {
                val currentCommand = parser.parse(readln())
                logger.log(currentCommand.execute(collection))

            } catch (e: IllegalArgumentException) {
                logger.log(PrintTypesEnum.WARNING to "Wrong argument type")
            } catch (e: RuntimeException) {
                logger.log(PrintTypesEnum.WARNING to e.message.toString())
            }
            catch (e: Exception) {
                logger.log(PrintTypesEnum.WARNING to e.message.toString())
            }
        }
    }

}