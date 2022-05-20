import driver.Car
import driver.Coordinates
import driver.HumanBeing
import driver.Mood
import storage.LocalHumanCollection
import java.time.LocalDate


fun main(args: Array<String>) {
    val new = LocalHumanCollection()
    new.add(
        HumanBeing(
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
    new.add(
        HumanBeing(
            "second",
            Coordinates(12, 12),
            LocalDate.now(),
            true,
            false,
            13,
            "12",
            5.0,
            Mood.APATHY,
            Car("12")
        )
    )
    new.add(
        HumanBeing(
            "third",
            Coordinates(1488, 1477),
            LocalDate.now(),
            realHero = true,
            hasToothPick = true,
            2,
            "asd",
            2.0,
            Mood.APATHY,
            Car("123")
        )
    )
    new.update(
        2,
        HumanBeing(
            "edited second",
            Coordinates(1488, 1477),
            LocalDate.now(),
            realHero = true,
            hasToothPick = true,
            14,
            "asd",
            2.0,
            Mood.APATHY,
            Car("123")
        )
    )

    new.update(
        1,
        HumanBeing(
            "edited fourth",
            Coordinates(1488, 1477),
            LocalDate.now(),
            realHero = true,
            hasToothPick = true,
            3,
            "asd",
            2.0,
            Mood.APATHY,
            Car("123")
        )
    )



    new.addIfMax(
        HumanBeing(
            "max impact seed",
            Coordinates(1488, 1477),
            LocalDate.now(),
            realHero = true,
            hasToothPick = true,
            212312,
            "as2323d",
            2.0,
            Mood.APATHY,
            Car("123")
        )
    )


    for (i in new.filterContainsSoundtrackName("asd")) {
        println(i)
    }
}