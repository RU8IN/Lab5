package utils

interface Logger {
    fun log(pair: Pair<PrintTypesEnum, String>)
    fun log(list: List<Pair<PrintTypesEnum, String>>)
}