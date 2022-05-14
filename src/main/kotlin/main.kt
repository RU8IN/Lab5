import driver.Car
import driver.Coordinates
import driver.HumanBeing
import driver.Mood
import storage.LocalHumanCollection
import storage.filterBy
import java.time.LocalDate


fun main(args: Array<String>) {
    val humanBeingArrayDeque = ArrayDeque<HumanBeing>()
    val haha = LocalHumanCollection()
    haha.add(
        HumanBeing(
            12,
            "second",
            Coordinates(12, 12),
            LocalDate.now(),
            true,
            true,
            12,
            "12",
            5.0,
            Mood.FRENZY,
            Car("12")
        )
    )

    haha.add(
        HumanBeing(
            5,
            "first",
            Coordinates(12, 12),
            LocalDate.now(),
            true,
            true,
            12,
            "12",
            5.0,
            Mood.FRENZY,
            Car("12")
        )
    )

    val k = haha.filterBy(HumanBeing::name) { a, b -> b.compareTo(a) }
    for (el in k) {
        println(el)
    }
}