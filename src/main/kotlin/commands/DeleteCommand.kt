package commands

import utils.PrintTypesEnum

class DeleteCommand() : SealedCommand() {


    override val name = "delete"
    override val help = "This command Deletes"

    override fun execute(): List<Pair<PrintTypesEnum, String>> {
        println(name)
        val list = mutableListOf<Pair<PrintTypesEnum, String>>()
        return list
    }

}