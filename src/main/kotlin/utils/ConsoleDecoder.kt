package utils

import exceptions.NoSuchMoodException
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.elementNames
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlin.system.exitProcess

class ConsoleDecoder(private val logger: Logger) : Decoder {
    /**
     * Context of the current serialization process, including contextual and polymorphic serialization and,
     * potentially, a format-specific configuration.
     */
    override val serializersModule: SerializersModule
        get() = EmptySerializersModule

    /**
     * Decodes the beginning of the nested structure in a serialized form
     * and returns [CompositeDecoder] responsible for decoding this very structure.
     *
     * Typically, classes, collections and maps are represented as a nested structure in a serialized form.
     * E.g. the following JSON
     * ```
     * {
     *     "a": 2,
     *     "b": { "nested": "c" }
     *     "c": [1, 2, 3],
     *     "d": null
     * }
     * ```
     * has three nested structures: the very beginning of the data, "b" value and "c" value.
     */
    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        return ConsoleCompositeDecoder(logger)
    }

    /**
     * Decodes a boolean value.
     * Corresponding kind is [PrimitiveKind.BOOLEAN].
     */
    override fun decodeBoolean(): Boolean {
        logger.log(PrintTypesEnum.QUESTION to "Введите поле типа BOOLEAN ")
        return readln().toBoolean()
    }

    /**
     * Decodes a single byte value.
     * Corresponding kind is [PrimitiveKind.BYTE].
     */
    override fun decodeByte(): Byte {
        logger.log(PrintTypesEnum.QUESTION to "Введите поле типа BYTE")
        return readln().toByte()
    }

    /**
     * Decodes a 16-bit unicode character value.
     * Corresponding kind is [PrimitiveKind.CHAR].
     */
    override fun decodeChar(): Char {
        return readln()[0]
    }

    /**
     * Decodes a 64-bit IEEE 754 floating point value.
     * Corresponding kind is [PrimitiveKind.DOUBLE].
     */
    override fun decodeDouble(): Double {
        logger.log(PrintTypesEnum.QUESTION to "Введите поле типа DOUBLE")
        return readln().toDouble()
    }

    /**
     * Decodes a enum value and returns its  in [enumDescriptor] elements collection.
     * Corresponding kind is [SerialKind.ENUM].
     *
     * E.g. for the enum `enum class Letters { A, B, C, D }` and
     * underlying input "C", [decodeEnum] method should return `2` as a result.
     *
     * This method does not imply any restrictions on the input format,
     * the format is free to store the enum by its name, index, ordinal or any other enum representation.
     */
    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
        logger.log(PrintTypesEnum.QUESTION to "Введите поле типа ENUM (${enumDescriptor.elementNames.joinToString()})")
        val result = enumDescriptor.elementNames.indexOf(readln())
        if (result == -1) throw NoSuchMoodException()
        return result
    }

    /**
     * Decodes a 32-bit IEEE 754 floating point value.
     * Corresponding kind is [PrimitiveKind.FLOAT].
     */
    override fun decodeFloat(): Float {
        return readln().toFloat()
    }

    /**
     * Returns [Decoder] for decoding an underlying type of an inline class.
     * [inlineDescriptor] describes a target inline class.
     *
     * Namely, for the `@Serializable inline class MyInt(val my: Int)`,
     * the following sequence is used:
     * ```
     * thisDecoder.decodeInline(MyInt.serializer().descriptor).decodeInt()
     * ```
     *
     * Current decoder may return any other instance of [Decoder] class,
     * depending on the provided [inlineDescriptor].
     * For example, when this function is called on Json decoder with
     * `UInt.serializer().descriptor`, the returned decoder is able
     * to decode unsigned integers.
     *
     * Note that this function returns [Decoder] instead of the [CompositeDecoder]
     * because inline classes always have the single property.
     * Calling [Decoder.beginStructure] on returned instance leads to an undefined behavior.
     */
    @ExperimentalSerializationApi
    override fun decodeInline(inlineDescriptor: SerialDescriptor): Decoder {
        TODO("Not yet implemented")
    }

    /**
     * Decodes a 32-bit integer value.
     * Corresponding kind is [PrimitiveKind.INT].
     */
    override fun decodeInt(): Int {
        logger.log(PrintTypesEnum.QUESTION to "Введите поле типа INT ")
        return readln().toInt()
    }

    /**
     * Decodes a 64-bit integer value.
     * Corresponding kind is [PrimitiveKind.LONG].
     */
    override fun decodeLong(): Long {
        logger.log(PrintTypesEnum.QUESTION to "Введите поле типа LONG ")
        return readln().toLong()

    }

    /**
     * Returns `true` if the current value in decoder is not null, false otherwise.
     * This method is usually used to decode potentially nullable data:
     * ```
     * // Could be String? deserialize() method
     * public fun deserialize(decoder: Decoder): String? {
     *     if (decoder.decodeNotNullMark()) {
     *         return decoder.decodeString()
     *     } else {
     *         return decoder.decodeNull()
     *     }
     * }
     * ```
     */
    @ExperimentalSerializationApi
    override fun decodeNotNullMark(): Boolean {
        return false
    }

    /**
     * Decodes the `null` value and returns it.
     *
     * It is expected that `decodeNotNullMark` was called
     * prior to `decodeNull` invocation and the case when it returned `true` was handled.
     */
    @ExperimentalSerializationApi
    override fun decodeNull(): Nothing? {
        return null
    }

    /**
     * Decodes a 16-bit short value.
     * Corresponding kind is [PrimitiveKind.SHORT].
     */
    override fun decodeShort(): Short {
        return readln().toShort()
    }

    /**
     * Decodes a string value.
     * Corresponding kind is [PrimitiveKind.STRING].
     */
    override fun decodeString(): String {
        logger.log(PrintTypesEnum.QUESTION to "Введите поле типа STRING")
        return readln()
    }
}


class ConsoleCompositeDecoder(private val logger: Logger) : CompositeDecoder {

    private var index: Int = 0

    /**
     * Context of the current decoding process, including contextual and polymorphic serialization and,
     * potentially, a format-specific configuration.
     */
    override val serializersModule: SerializersModule
        get() = EmptySerializersModule


    private inline fun logWithQuestion(descriptor: SerialDescriptor, index: Int) {
        logger.log(
            PrintTypesEnum.QUESTION to
                    "Введите поле ${descriptor.getElementName(index)} типа ${descriptor.getElementDescriptor(index).kind}"
        )
    }


    /**
     * Decodes a boolean value from the underlying input.
     * The resulting value is associated with the [descriptor] element at the given [index].
     * The element at the given index should have [PrimitiveKind.BOOLEAN] kind.
     */
    override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
        logWithQuestion(descriptor, index)
        return readln().toBoolean()
    }

    /**
     * Decodes a single byte value from the underlying input.
     * The resulting value is associated with the [descriptor] element at the given [index].
     * The element at the given index should have [PrimitiveKind.BYTE] kind.
     */
    override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
        logWithQuestion(descriptor, index)
        return readln().toByte()
    }

    /**
     * Decodes a 16-bit unicode character value from the underlying input.
     * The resulting value is associated with the [descriptor] element at the given [index].
     * The element at the given index should have [PrimitiveKind.CHAR] kind.
     */
    override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
        logWithQuestion(descriptor, index)
        return readln()[0]
    }

    /**
     * Decodes a 64-bit IEEE 754 floating point value from the underlying input.
     * The resulting value is associated with the [descriptor] element at the given [index].
     * The element at the given index should have [PrimitiveKind.DOUBLE] kind.
     */
    override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
        logWithQuestion(descriptor, index)
        return readln().toDouble()
    }

    /**
     *  Decodes the index of the next element to be decoded.
     *  Index represents a position of the current element in the serial descriptor element that can be found
     *  with [SerialDescriptor.getElementIndex].
     *
     *  If this method returns non-negative index, the caller should call one of the `decode*Element` methods
     *  with a resulting index.
     *  Apart from positive values, this method can return [DECODE_DONE] to indicate that no more elements
     *  are left or [UNKNOWN_NAME] to indicate that symbol with an unknown name was encountered.
     *
     * Example of usage:
     * ```
     * class MyPair(i: Int, d: Double)
     *
     * object MyPairSerializer : KSerializer<MyPair> {
     *     // ... other methods omitted
     *
     *    fun deserialize(decoder: Decoder): MyPair {
     *        val composite = decoder.beginStructure(descriptor)
     *        var i: Int? = null
     *        var d: Double? = null
     *        while (true) {
     *            when (val index = composite.decodeElementIndex(descriptor)) {
     *                0 -> i = composite.decodeIntElement(descriptor, 0)
     *                1 -> d = composite.decodeDoubleElement(descriptor, 1)
     *                DECODE_DONE -> break // Input is over
     *                else -> error("Unexpected index: $index)
     *            }
     *        }
     *        composite.endStructure(descriptor)
     *        require(i != null && d != null)
     *        return MyPair(i, d)
     *    }
     * }
     * ```
     * This example is a rough equivalent of what serialization plugin generates for serializable pair class.
     *
     * The need in such a loop comes from unstructured nature of most serialization formats.
     * For example, JSON for the following input `{"d": 2.0, "i": 1}`, will first read `d` key with index `1`
     * and only after `i` with the index `0`.
     *
     * A potential implementation of this method for JSON format can be the following:
     * ```
     * fun decodeElementIndex(descriptor: SerialDescriptor): Int {
     *     // Ignore arrays
     *     val nextKey: String? = myStringJsonParser.nextKey()
     *     if (nextKey == null) return DECODE_DONE
     *     return descriptor.getElementIndex(nextKey) // getElementIndex can return UNKNOWN_NAME
     * }
     * ```
     *
     * If [decodeSequentially] returns `true`, the caller might skip calling this method.
     */
    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        if (this.index >= descriptor.elementsCount) return CompositeDecoder.DECODE_DONE
        return this.index++
    }

    /**
     * Decodes a 32-bit IEEE 754 floating point value from the underlying input.
     * The resulting value is associated with the [descriptor] element at the given [index].
     * The element at the given index should have [PrimitiveKind.FLOAT] kind.
     */
    override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
        logWithQuestion(descriptor, index)
        return readln().toFloat()
    }

    /**
     * Returns [Decoder] for decoding an underlying type of an inline class.
     * Serializable inline class is described by the [child descriptor][SerialDescriptor.getElementDescriptor]
     * of given [descriptor] at [index].
     *
     * Namely, for the `@Serializable inline class MyInt(val my: Int)`,
     * and `@Serializable class MyData(val myInt: MyInt)`
     * the following sequence is used:
     * ```
     * thisDecoder.decodeInlineElement(MyData.serializer().descriptor, 0).decodeInt()
     * ```
     *
     * This method provides an opportunity for the optimization and its invocation should be identical to
     * ```
     * thisDecoder.decodeSerializableElement(MyData.serializer.descriptor, 0, MyInt.serializer())
     * ```
     *
     * Current decoder may return any other instance of [Decoder] class, depending on the provided descriptor.
     * For example, when this function is called on Json decoder with descriptor that has
     * `UInt.serializer().descriptor` at the given [index], the returned decoder is able
     * to decode unsigned integers.
     *
     * Note that this function returns [Decoder] instead of the [CompositeDecoder]
     * because inline classes always have the single property.
     * Calling [Decoder.beginStructure] on returned instance leads to an undefined behavior.
     *
     * @see Decoder.decodeInline
     * @see SerialDescriptor.getElementDescriptor
     */
    @ExperimentalSerializationApi
    override fun decodeInlineElement(descriptor: SerialDescriptor, index: Int): Decoder {
        TODO("Not yet implemented")
    }

    /**
     * Decodes a 32-bit integer value from the underlying input.
     * The resulting value is associated with the [descriptor] element at the given [index].
     * The element at the given index should have [PrimitiveKind.INT] kind.
     */
    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
        logWithQuestion(descriptor, index)
        return readln().toInt()
    }

    /**
     * Decodes a 64-bit integer value from the underlying input.
     * The resulting value is associated with the [descriptor] element at the given [index].
     * The element at the given index should have [PrimitiveKind.LONG] kind.
     */
    override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
        logWithQuestion(descriptor, index)
        return readln().toLong()
    }

    /**
     * Decodes nullable value of the type [T] with the given [deserializer].
     *
     * If value at given [index] was already decoded with previous [decodeSerializableElement] call with the same index,
     * [previousValue] would contain a previously decoded value.
     * This parameter can be used to aggregate multiple values of the given property to the only one.
     * Implementation can safely ignore it and return a new value, efficiently using 'the last one wins' strategy,
     * or apply format-specific aggregating strategies, e.g. appending scattered Protobuf lists to a single one.
     */
    @ExperimentalSerializationApi
    override fun <T : Any> decodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T?>,
        previousValue: T?
    ): T? {
        logger.log(
            PrintTypesEnum.INFO to
                    "Ввод объекта: ${descriptor.getElementName(index)}"
        )
        return deserializer.deserialize(ConsoleDecoder(logger))
    }

    /**
     * Decodes value of the type [T] with the given [deserializer].
     *
     * Implementations of [CompositeDecoder] may use their format-specific deserializers
     * for particular data types, e.g. handle [ByteArray] specifically if format is binary.
     *
     * If value at given [index] was already decoded with previous [decodeSerializableElement] call with the same index,
     * [previousValue] would contain a previously decoded value.
     * This parameter can be used to aggregate multiple values of the given property to the only one.
     * Implementation can safely ignore it and return a new value, effectively using 'the last one wins' strategy,
     * or apply format-specific aggregating strategies, e.g. appending scattered Protobuf lists to a single one.
     */
    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ): T {
        logger.log(
            PrintTypesEnum.INFO to
                    "Ввод объекта: ${descriptor.getElementName(index)}"
        )
        return deserializer.deserialize(ConsoleDecoder(logger))
    }

    /**
     * Decodes a 16-bit short value from the underlying input.
     * The resulting value is associated with the [descriptor] element at the given [index].
     * The element at the given index should have [PrimitiveKind.SHORT] kind.
     */
    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
        logWithQuestion(descriptor, index)
        return readln().toShort()
    }

    /**
     * Decodes a string value from the underlying input.
     * The resulting value is associated with the [descriptor] element at the given [index].
     * The element at the given index should have [PrimitiveKind.STRING] kind.
     */
    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
        logWithQuestion(descriptor, index)
        return readln()
    }

    /**
     * Denotes the end of the structure associated with current decoder.
     * For example, composite decoder of JSON format will expect (and parse)
     * a closing bracket in the underlying input.
     */
    override fun endStructure(descriptor: SerialDescriptor) {
    }

}


