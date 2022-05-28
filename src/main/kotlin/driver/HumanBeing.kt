package driver

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable(with=HumanBeingSerializer::class)
data class HumanBeing(
    val name: String,
    val coordinates: Coordinates,
    val realHero: Boolean?,
    val hasToothPick: Boolean,
    val impactSeed: Long,
    val soundtrackName: String,
    val minutesOfWaiting: Double,
    val mood: Mood,
    val car: Car
    ) : Comparable<HumanBeing> {

    val creationDate: Instant = Clock.System.now()
    var id: Long? = null

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