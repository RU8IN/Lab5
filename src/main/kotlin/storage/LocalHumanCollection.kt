package storage

import driver.HumanBeing
import exceptions.noHumanBeingWithSuchIdException
import java.time.LocalDate
import java.util.ArrayDeque

class LocalHumanCollection : AbstractHumanCollection {

    private val initializationDate: LocalDate = LocalDate.now()
    private val arrayDeque = ArrayDeque<HumanBeing>()


    override fun removeById(id: Long): Boolean {
        val iter = arrayDeque.iterator()
        for (el in iter) {
            if (el.id == id) {
                iter.remove()
                return true
            }
        }
        throw noHumanBeingWithSuchIdException()

    }

    override fun update(id: Long, humanBeing: HumanBeing): Boolean {
        val iter = this.arrayDeque.iterator()

        for (el in iter) {
            if (el.id == id) {
                iter.remove()
                this.add(humanBeing)
                return true
            } else if (!iter.hasNext()) {
                throw noHumanBeingWithSuchIdException()
            }
        }
        return false
    }

    override fun sorted(): List<HumanBeing> = this.arrayDeque.sorted()

    override fun addIfMax(humanBeing: HumanBeing): Boolean {

        val humanBeingWithMaxImpactSeed: HumanBeing? = this.arrayDeque.maxByOrNull { it.impactSeed }

        return if (humanBeingWithMaxImpactSeed == null || humanBeing > humanBeingWithMaxImpactSeed) {
            this.add(humanBeing)
            true
        } else {
            false
        }
    }

    override fun removeLower(humanBeing: HumanBeing) {
        val iterator = this.arrayDeque.iterator()
        for (el in iterator) {
            if (el < humanBeing) {
                iterator.remove()
            }
        }
    }

    override fun removeAnyByImpactSeed(impactSpeed: Long) {
        val iterator = this.arrayDeque.iterator()

        for (el in iterator) {
            if (impactSpeed == el.impactSeed) {
                iterator.remove()
                return
            }
        }
    }

    override fun filterContainsSoundtrackName(soundtrackName: String): List<HumanBeing> {
        return this.arrayDeque.filter { it.soundtrackName == soundtrackName }
    }

    override fun getById(id: Long): HumanBeing {
        return this.arrayDeque.find { it.id == id } ?: throw noHumanBeingWithSuchIdException()
    }

    override fun add(humanBeing: HumanBeing): Boolean {
        humanBeing.id = getMaxIdInCollection() + 1
        this.arrayDeque.add(humanBeing)
        return true
    }

    override fun getInfo(): String {
        val info = """
            Type: ${this::class} : ArrayDeque
            Initialization Date: $initializationDate
            Size: ${this.arrayDeque.size}
        """.trimIndent()
        return info
    }

    private fun getMaxIdInCollection(): Long {
        if (this.arrayDeque.size != 0) {
            return this.arrayDeque.maxOf { it.id!! }
        }
        return 0
    }

    override fun iterator(): Iterator<HumanBeing> = this.arrayDeque.iterator()

    override fun toString(): String {
        return this.arrayDeque.toString()
    }
}