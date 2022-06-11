package driver

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import utils.ANSIColors

@SerialName("Human Being")
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
                Human: ${ANSIColors.CYAN}$name${ANSIColors.RESET}
                Coordinates: ${ANSIColors.CYAN}$coordinates${ANSIColors.RESET}
                Real Hero: ${ANSIColors.CYAN}$realHero${ANSIColors.RESET}
                Has Tooth Pick: ${ANSIColors.CYAN}$hasToothPick${ANSIColors.RESET}
                Impact Seed: ${ANSIColors.CYAN}$impactSeed${ANSIColors.RESET}
                Soundtrack Name: ${ANSIColors.CYAN}$soundtrackName${ANSIColors.RESET}
                Minutes of Waiting: ${ANSIColors.CYAN}$minutesOfWaiting${ANSIColors.RESET}
                Mood: ${ANSIColors.CYAN}$mood${ANSIColors.RESET}
                Car: ${ANSIColors.CYAN}$car${ANSIColors.RESET}
                Id: ${ANSIColors.CYAN}$id${ANSIColors.RESET}
                Creation Date: ${ANSIColors.CYAN}$creationDate${ANSIColors.RESET}
            """.trimIndent()
    }
}