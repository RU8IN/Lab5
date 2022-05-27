package driver

import exceptions.IDExceptionLessThanZero
import exceptions.idNotSetException
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class HumanBeing(
    val name: String,
    val coordinates: Coordinates,
    val creationDate: LocalDate,
    val realHero: Boolean?,
    val hasToothPick: Boolean,
    val impactSeed: Long,
    val soundtrackName: String,
    val minutesOfWaiting: Double,
    val mood: Mood,
    val car: Car
    ) : Comparable<HumanBeing> {

    var id: Long? = null

    override fun compareTo(other: HumanBeing): Int = this.impactSeed.compareTo(other.impactSeed)

}