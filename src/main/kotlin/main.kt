import driver.*
import kotlinx.serialization.descriptors.elementNames
import storage.LocalHumanCollection


val testHuman = HumanBeing(
    "name",
    Coordinates(1, 2),
    true,
    true,
    12L,
    "123",
    5.5,
    Mood.FRENZY,
    Car("SDasd")
)
fun main(args: Array<String>) {
    val logger = ConsoleLogger
    val parser = Parser(logger)
    val col = LocalHumanCollection

    val app = ConsoleApplication(parser, logger, col)

    app.run()
}
