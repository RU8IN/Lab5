package client

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.util.cio.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import storage.HumanCollectionInterface
import storage.LocalHumanCollection
import utils.*
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import java.nio.ByteBuffer
import java.nio.charset.Charset
import kotlin.system.exitProcess

fun main() {
    runBlocking {
        val selectorManager = SelectorManager(Dispatchers.IO)
        val socket = aSocket(selectorManager).tcp().connect("127.0.0.1", 9003)

        val receiveChannel = socket.openReadChannel()
        val sendChannel = socket.openWriteChannel(autoFlush = true)

        launch(Dispatchers.IO) {
            while (true) {
                val data = ByteArray(1024 * 1024)
                receiveChannel.readAvailable(data, 0, 1024 * 1024)
                println(data.decodeToString().filter { i -> i.code != 0 })
//                } else {
//                    println("Server closed a connection")
//                    socket.close()
//                    selectorManager.close()
//                    exitProcess(0)
//                }
            }
        }

        val logger: Logger = ConsoleLogger
        val parser: ParserInterface = Parser(logger)
        val collection: HumanCollectionInterface = LocalHumanCollection()
        val collectionPath: String? = null

        while (true) {
            var yourBytes: ByteArray

//            try {
            val currentCommand = parser.parse(readln())

            val byteBuffer = ByteBuffer.allocate(currentCommand.toByteArray().size)
            byteBuffer.put(currentCommand.toByteArray())
            byteBuffer.flip()
            sendChannel.writeAvailable(byteBuffer)

//            } catch (e: IllegalArgumentException) {
//                logger.log(PrintTypesEnum.WARNING to "Wrong argument type")
//            } catch (e: RuntimeException) {
//                logger.log(PrintTypesEnum.WARNING to e.message.toString())
//            }
//            catch (e: Exception) {
//                logger.log(PrintTypesEnum.WARNING to e.message.toString())
//            }

        }
    }
}