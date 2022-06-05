import driver.*
import kotlinx.serialization.json.Json
import storage.LocalHumanCollection
import utils.ConsoleApplication
import utils.ConsoleLogger
import utils.Parser


fun main(args: Array<String>) {
    val logger = ConsoleLogger
    val parser = Parser(logger)
    val col = LocalHumanCollection()

    val app = ConsoleApplication(parser, logger, col, args.getOrNull(0))
    app.run()

}
