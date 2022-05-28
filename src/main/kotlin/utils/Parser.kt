package utils

import commands.*
import exceptions.NoSuchCommandException
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.valueParameters

class Parser() : ParserInterface {
    override fun parse(string: String): SealedCommand {
        val tokenizer = StringTokenizer(string)
        val command = tokenizer.nextToken()
        val list = mutableListOf<String>()
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken())
        }

        when (command) {
            "add" -> return AddCommand.serializer().deserialize(NewDecoder(list.joinToString(separator =" ")))
            "clear" -> return ClearCommand.serializer().deserialize(NewDecoder(list.joinToString(separator =" ")))
            "remove_by_id" -> return RemoveByIdCommand.serializer().deserialize(NewDecoder(list.joinToString(separator =" ")))
        }

        throw NoSuchCommandException()
    }
}


//val tokenizer = StringTokenizer(string)
//val commandToken = tokenizer.nextToken()
//
//val allCommandsClasses = SealedCommand::class.sealedSubclasses
//val sealedClassFields = SealedCommand::class.memberProperties.associate { it.name to it.returnType }
//for (clazz in allCommandsClasses) {
//    val map = clazz.memberProperties.associate { it.name to it.returnType }.toMutableMap()
//    sealedClassFields.forEach {
//        map.remove(it.key)
//    }
//    println(clazz.simpleName)
//    println(map)