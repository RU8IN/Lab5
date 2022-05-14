package driver

import exceptions.IDExceptionLessThanZero
import exceptions.idNotSetException
import kotlinx.serialization.Serializable
import java.time.LocalDate


@Serializable
data class HumanBeing(
    var _id: Long?,
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

    init {
        if (this.id <= 0) {
            throw IDExceptionLessThanZero()
        }
    }

    public val id
        get() = this._id ?: throw idNotSetException()

    override fun compareTo(other: HumanBeing): Int = this.id.compareTo(other.id)

}