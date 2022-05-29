package utils



object ConsoleLogger: Logger {

    override fun log(list: List<Pair<PrintTypesEnum, String>>) {
        for (el in list) {
            val infoType = el.first.sign
            val infoValue = el.second
            println("[$infoType] $infoValue")
        }
    }

    override fun log(pair: Pair<PrintTypesEnum, String>) {
        val infoType = pair.first.sign
        val infoValue = pair.second
        println("[$infoType] $infoValue")
    }

}