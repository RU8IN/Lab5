package driver

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable(with = HumanBeingSerializer::class)
data class HumanBeing(
    @SerialName("Name")
    val name: String,
    @SerialName("Coordinates")
    val coordinates: Coordinates,
    @SerialName("Real Hero")
    val realHero: Boolean?,
    @SerialName("Has Tooth Pick")
    val hasToothPick: Boolean,
    @SerialName("Impact Seed")
    val impactSeed: Long,
    @SerialName("Soundtrack Name")
    val soundtrackName: String,
    @SerialName("Minutes of Waiting")
    val minutesOfWaiting: Double,
    @SerialName("Mood")
    val mood: Mood,
    @SerialName("Car Name")
    val car: Car,
    val creationDate: Instant = Clock.System.now(),
    var id: Long? = null
) : Comparable<HumanBeing> {



    override fun compareTo(other: HumanBeing): Int = this.impactSeed.compareTo(other.impactSeed)

    override fun toString(): String {
        return """
                Human: $name
                Coordinates: $coordinates
                Real Hero: $realHero
                Has Tooth Pick: $hasToothPick
                Impact Seed: $impactSeed
                Soundtrack Name: $soundtrackName
                Minutes of Waiting: $minutesOfWaiting
                Mood: $mood
                Car: $car
                Id: $id
                Creation Date: $creationDate
            """.trimIndent()
    }
}