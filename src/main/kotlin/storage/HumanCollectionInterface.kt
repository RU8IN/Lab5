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
    fun loadCollection(newCollection: LocalHumanCollection)
    fun executeScript()
}


fun <T> HumanCollectionInterface.filterBy(
    getter: KProperty1<HumanBeing, T>,
    comparator: (T, T) -> Int
): List<HumanBeing> {

    val list = this.toMutableList()

    for (i in list.indices) {
        for (j in 0 until i) {
            if (comparator(getter(list[i]), getter(list[j])) > 0) {
                val a = list[j]
                list[j] = list[i]
                list[i] = a
            }
        }
    }
    return list
}