package commands

sealed class SealedCommand() : CommandInterface {

    abstract val name: String
    abstract val help: String

}

