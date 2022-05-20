package storage

import driver.HumanBeing
import kotlin.reflect.KProperty1

interface AbstractHumanCollection : Iterable<HumanBeing> {

    fun removeById(id: Long): Boolean
    fun update(id: Long, humanBeing: HumanBeing): Boolean
    fun sorted(): List<HumanBeing>
    fun addIfMax(humanBeing: HumanBeing): Boolean
    fun removeLower(humanBeing: HumanBeing)
    fun removeAnyByImpactSeed(impactSpeed: Long)
    fun filterContainsSoundtrackName(soundtrackName: String): List<HumanBeing>
    fun getById(id: Long): HumanBeing?
    fun add(humanBeing: HumanBeing): Boolean
    fun getInfo(): String
}


fun <T> AbstractHumanCollection.filterBy(
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