package utils

class Printer {

    fun print(list: List<Pair<PrintTypesEnum, String>>) {
        for (el in list) {
            val infoType = el.first.sign
            val infoValue = el.second
            println("[$infoType] $infoValue")
        }
    }
}