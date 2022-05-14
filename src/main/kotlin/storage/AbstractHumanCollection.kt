package storage

import driver.HumanBeing
import kotlin.reflect.KProperty1

interface AbstractHumanCollection : Iterable<HumanBeing> {

    fun delete(id: Int)
    fun edit(id: Int, humanBeing: HumanBeing)
    fun sorted(): List<HumanBeing>
    fun removeFirst(): HumanBeing
    fun addIfMax(humanBeing: HumanBeing)
    fun removeLower(humanBeing: HumanBeing)
    fun removeAnyByImpactSeed(impactSpeed: Int)
    fun filterContainsSoundtrackName(soundtrackName: String): List<HumanBeing>
    fun filterByDescendingMood(): List<HumanBeing>
}


fun<T> AbstractHumanCollection.filterBy(
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