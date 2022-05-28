import commands.*
import driver.Car
import driver.Coordinates
import driver.HumanBeing
import driver.Mood
import storage.LocalHumanCollection
import sun.util.calendar.LocalGregorianCalendar
import utils.ConsoleApplication
import utils.ConsoleLogger
import utils.Parser

val testHumanBeing = HumanBeing(
    "123",
    Coordinates(1, 2),
    realHero = true,
    hasToothPick = true,
    impactSeed = 123L,
    soundtrackName = "123",
    minutesOfWaiting = 12.2,
    mood = Mood.SADNESS,
    car = Car("12")
)

val testHumanBeing2 = HumanBeing(
    "321",
    Coordinates(1, 2),
    realHero = true,
    hasToothPick = true,
    impactSeed = 123L,
    soundtrackName = "123",
    minutesOfWaiting = 12.2,
    mood = Mood.SADNESS,
    car = Car("12")
)

fun main(args: Array<String>) {
//    val app = ConsoleApplication(parser = Parser(), logger=ConsoleLogger())
//    app.run()
//    val col = LocalHumanCollection()
//    var add = AddCommand(testHumanBeing, col)
//    add.execute()
//    add = AddCommand(testHumanBeing2, col)
//    add.execute()
//    val show = ShowCommand(col)
//    for (i in show.execute()) {
//        println(i)
//    }

    val col = LocalHumanCollection()
    col.add(testHumanBeing)
    col.add(testHumanBeing2)
    val parser = Parser()
    parser.parse("add 321 1 2 true true 123 123 12.2 SADNESS 12").execute(col)
//    val rem = RemoveByIdCommand(1L).execute(col)
    println(col)

}
