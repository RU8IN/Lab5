package commands

import utils.PrintTypesEnum

interface CommandInterface {
    fun execute(): List<Pair<PrintTypesEnum, String>>
}
