import Serialization.decodeFromList
import Serialization.encodeToList
import driver.*
import kotlinx.serialization.json.Json
import storage.LocalHumanCollection
import utils.ConsoleApplication
import utils.ConsoleLogger
import utils.Parser

val human = HumanBeing(
    "Kavinsky",
    Coordinates(1, 2), true,
    true,
    12L, "12312",
    5.05,
    Mood.SORROW,
    Car("name")
)

fun main(args: Array<String>) {
    val logger = ConsoleLogger
    val parser = Parser(logger)
    val col = LocalHumanCollection()


    val app = ConsoleApplication(parser, logger, col)
    app.run()

//    println(col)

//    val obj = decodeFromList<HumanBeing>(encodeToList(human))
//    println(obj)
}
