package commands

import driver.HumanBeing
import utils.PrintTypesEnum


class AddCommand(private val humanBeing: HumanBeing, private val arrayDeque: ArrayDeque<HumanBeing>) : SealedCommand() {

    override val name = "add"
    override val help = "This command adds object to deque"

    override fun execute(): List<Pair<PrintTypesEnum, String>> {

        arrayDeque.add(humanBeing)

        val list = mutableListOf<Pair<PrintTypesEnum, String>>()
        list.add(Pair(PrintTypesEnum.INFO, "Human has been added successfully!"))
        return list
    }
}