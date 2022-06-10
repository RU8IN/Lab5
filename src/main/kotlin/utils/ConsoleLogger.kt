package utils



object ConsoleLogger: Logger {

    override fun log(list: List<Pair<PrintTypesEnum, String>>) {
        for (el in list) {
            val infoType = el.first
            val infoValue = el.second
            println("$infoType $infoValue")
        }
    }

    override fun log(pair: Pair<PrintTypesEnum, String>) {
        val infoType = pair.first
        val infoValue = pair.second
        println("$infoType $infoValue")
    }

}