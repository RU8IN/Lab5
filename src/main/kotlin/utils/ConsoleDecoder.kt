package utils

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.elementNames
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

class ConsoleDecoder(val strings: List<String>) : CompositeDecoder {
    override val serializersModule: SerializersModule
        get() = EmptySerializersModule

    var index: Int = 0

    override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
        return strings[index].toBoolean()
    }

    override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
        return strings[index].toByte()
    }

    override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
        if (strings[index].length != 1) throw SerializationException("String length more than 1")
        return strings[index][0]
    }

    override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
        return strings[index].toDouble()
    }

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        if (this.index < strings.size) return this.index++
        else {
            return CompositeDecoder.DECODE_DONE
        }
    }

    override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
        return strings[index].toFloat()
    }

    @ExperimentalSerializationApi
    override fun decodeInlineElement(descriptor: SerialDescriptor, index: Int): Decoder {
        TODO("")
    }

    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
        return strings[index].toInt()
    }

    override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
        return strings[index].toLong()
    }

    @ExperimentalSerializationApi
    override fun <T : Any> decodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T?>,
        previousValue: T?
    ): T? {
        TODO("Not yet implemented")
    }

    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ): T {
        return deserializer.deserialize(NewDecoder(strings.slice(index until (index+descriptor.elementsCount)).joinToString(separator = " "))).also { this.index += descriptor.elementsCount }
    }

    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
        return strings[index].toShort()
    }

    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
        return strings[index].toString()
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        if (index != strings.size) throw SerializationException("Structure ended somehow")
    }
}





class NewDecoder(val string: String) : Decoder {
    override val serializersModule: SerializersModule
        get() = EmptySerializersModule

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        if (string == "") return ConsoleDecoder(emptyList())
        else {
            return ConsoleDecoder(string.split(' '))
        }
    }

    override fun decodeBoolean(): Boolean {
        return string.toBoolean()
    }

    override fun decodeByte(): Byte {
        return string.toByte()
    }

    override fun decodeChar(): Char {
        if (string.length != 1) throw SerializationException("Not char")
        return string[0]
    }

    override fun decodeDouble(): Double {
        return string.toDouble()
    }

    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
        return enumDescriptor.elementNames.indexOf(string)
    }

    override fun decodeFloat(): Float {
        return string.toFloat()
    }

    @ExperimentalSerializationApi
    override fun decodeInline(inlineDescriptor: SerialDescriptor): Decoder {
        TODO("")
    }

    override fun decodeInt(): Int {
        return string.toInt()
    }

    override fun decodeLong(): Long {
        return string.toLong()
    }

    @ExperimentalSerializationApi
    override fun decodeNotNullMark(): Boolean {
        return string.toBoolean()
    }

    @ExperimentalSerializationApi
    override fun decodeNull(): Nothing? {
        return null
    }

    override fun decodeShort(): Short {
        return string.toShort()
    }

    override fun decodeString(): String {
        return string
    }

}