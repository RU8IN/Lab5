package storage

import driver.HumanBeing
import java.util.ArrayDeque

class LocalHumanCollection : ArrayDeque<HumanBeing>(), AbstractHumanCollection {
    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override fun edit(id: Int, humanBeing: HumanBeing) {
        TODO("Not yet implemented")
    }

    override fun sorted(): List<HumanBeing> {
        TODO("Not yet implemented")
    }

    override fun removeFirst(): HumanBeing {
        TODO("Not yet implemented")
    }

    override fun addIfMax(humanBeing: HumanBeing) {
        TODO("Not yet implemented")
    }

    override fun removeLower(humanBeing: HumanBeing) {
        TODO("Not yet implemented")
    }

    override fun removeAnyByImpactSeed(impactSpeed: Int) {
        TODO("Not yet implemented")
    }

    override fun filterContainsSoundtrackName(soundtrackName: String): List<HumanBeing> {
        TODO("Not yet implemented")
    }

    override fun filterByDescendingMood(): List<HumanBeing> {
        TODO("Not yet implemented")
    }

}