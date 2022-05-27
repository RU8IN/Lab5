import javafx.application.Application
import utils.ConsoleApplication
import utils.ConsoleLogger
import utils.Parser
import kotlin.properties.Delegates

class Logger() {
    fun log(str: String) {
        println("Logged $str")
    }
}

class User(private val logger: Logger) {




}


suspend fun main(args: Array<String>) {
    val app = ConsoleApplication(parser = Parser(), logger = ConsoleLogger())
    app.run()

}
