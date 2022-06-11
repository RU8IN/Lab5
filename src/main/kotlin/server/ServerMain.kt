package server

import commands.SealedCommand
import commands.fromByteArray
import commands.name
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.util.cio.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import io.ktor.utils.io.jvm.javaio.*
import io.netty.buffer.ByteBuf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import storage.HumanCollectionInterface
import storage.LocalHumanCollection
import utils.ConsoleLogger
import utils.Logger
import utils.Parser
import utils.ParserInterface

fun main() {
    val logger: Logger = ConsoleLogger
    val parser: ParserInterface = Parser(logger)
    val collection: HumanCollectionInterface = LocalHumanCollection()
    val collectionPath: String? = null

    runBlocking {
        val selectorManager = SelectorManager(Dispatchers.IO)
        val serverSocket = aSocket(selectorManager).tcp().bind("127.0.0.1", 9003)
        println("Server is listening at ${serverSocket.localAddress}")
        while (true) {
            val socket = serverSocket.accept()
            println("Accepted $socket")
            launch {
                val receiveChannel = socket.openReadChannel()
                val sendChannel = socket.openWriteChannel(autoFlush = true)
                val data = ByteArray(1024*1024)
                try {
                    while (true) {
                        receiveChannel.readAvailable(data, 0, 1024*1024)
                        val command = fromByteArray(data)
                        println(command.execute(collection).toString())
                        sendChannel.writeStringUtf8(command.execute(collection).toString())
                    }
                } catch (e: Exception) {
                    println(e.toString())
                    socket.close()
                }
            }
        }
    }
}