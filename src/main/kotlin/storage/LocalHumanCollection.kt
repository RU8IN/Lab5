package storage

import driver.HumanBeing
import exceptions.CollectionIsEmptyException
import exceptions.NoHumanBeingWithSuchIdException
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import java.util.ArrayDeque

@kotlinx.serialization.Serializable
object LocalHumanCollection : HumanCollectionInterface {

    private val initializationDate: Instant = Clock.System.now()
    private val arrayDeque: ArrayDeque<HumanBeing> = ArrayDeque<HumanBeing>()

    override fun removeById(id: Long) {
        val iter = arrayDeque.iterator()
        for (el in iter) {
            if (el.id == id) {
                iter.remove()
                return
            }
        }
        throw NoHumanBeingWithSuchIdException(id)
    }

    override fun update(id: Long, humanBeing: HumanBeing) {
        val iter = this.arrayDeque.iterator()
        println(iter)
        for (el in iter) {
            if (el.id == id) {
                iter.remove()
                this.add(humanBeing)
                return
            }
        }
        throw NoHumanBeingWithSuchIdException(id)
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

    override fun sortedByDescendingMood(): List<HumanBeing> {
        return this.arrayDeque.sortedBy { it.mood }.reversed()
    }

    override fun getById(id: Long): HumanBeing {
        return this.arrayDeque.find { it.id == id } ?: throw NoHumanBeingWithSuchIdException(id)
    }

    override fun clear() {
        if (this.arrayDeque.isEmpty()) throw CollectionIsEmptyException()
        this.arrayDeque.clear()
    }

    override fun add(humanBeing: HumanBeing) {
        humanBeing.id = getMaxIdInCollection() + 1
        this.arrayDeque.add(humanBeing)
    }

    override fun getInfo(): String {
        return """
            Type: ${this::class} : ArrayDeque
            Initialization Date: $initializationDate
            Size: ${arrayDeque.size}
        """.trimIndent()
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

    fun toList(): List<HumanBeing> {
        val list = mutableListOf<HumanBeing>()
        this.arrayDeque.forEach { list.add(it) }
        return list
    }


    override fun removeFirst() {
        if (this.arrayDeque.isEmpty()) throw CollectionIsEmptyException()
        this.arrayDeque.removeFirst()
    }

}

