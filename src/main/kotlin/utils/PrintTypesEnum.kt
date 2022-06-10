package utils

enum class PrintTypesEnum(open val sign: String, protected val color: ANSIColors) {


    WARNING("!", ANSIColors.RED),
    INFO("+", ANSIColors.GREEN),
    QUESTION("?", ANSIColors.YELLOW);

    override fun toString(): String {
        return "${this.color}[${this.sign}]${ANSIColors.RESET}"
    }
}