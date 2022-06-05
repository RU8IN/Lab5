package storage

import driver.HumanBeing
import kotlin.reflect.KProperty1

interface HumanCollectionInterface : Iterable<HumanBeing> {

    fun getInfo(): String
    fun add(humanBeing: HumanBeing)
    fun update(id: Long, humanBeing: HumanBeing)
    fun removeById(id: Long)
    fun sorted(): List<HumanBeing>
    fun addIfMax(humanBeing: HumanBeing): Boolean
    fun removeLower(humanBeing: HumanBeing)
    fun removeAnyByImpactSeed(impactSpeed: Long)
    fun filterContainsSoundtrackName(soundtrackName: String): List<HumanBeing>
    fun sortedByDescendingMood(): List<HumanBeing>
    fun getById(id: Long): HumanBeing?
    fun clear()
    fun removeFirst()
    fun isEmpty(): Boolean
    fun loadCollection(newCollectionPath: String)
    fun executeScript()
}