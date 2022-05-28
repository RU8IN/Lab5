package utils

import commands.SealedCommand

interface ParserInterface {
    fun parse(string: String): SealedCommand
}