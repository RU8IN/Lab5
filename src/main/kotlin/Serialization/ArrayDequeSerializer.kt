package Serialization

import kotlinx.serialization.*
import kotlin.collections.ArrayDeque
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = ArrayDeque::class)
class ArrayDequeSerializer<E>(private val dataSerializer: KSerializer<E>) : KSerializer<ArrayDeque<E>> {
    fun builder(): ArrayList<E> = arrayListOf()
    private fun ArrayList<E>.toResult(): ArrayDeque<E> {
        val ArrayDeque = ArrayDeque<E>()
        for (i in this) {
            ArrayDeque.add(i)
        }
        return ArrayDeque
    }

    private fun merge(decoder: Decoder): ArrayDeque<E> {
        val builder = builder()
        val startIndex = builder.size
        val compositeDecoder = decoder.beginStructure(descriptor)
        if (compositeDecoder.decodeSequentially()) {
            readAll(compositeDecoder, builder, startIndex, readSize(compositeDecoder, builder))
        } else {
            while (true) {
                val index = compositeDecoder.decodeElementIndex(descriptor)
                if (index == CompositeDecoder.DECODE_DONE) break
                readElement(compositeDecoder, startIndex + index, builder)
            }
        }
        compositeDecoder.endStructure(descriptor)
        return builder.toResult()
    }

    override val descriptor: SerialDescriptor = ArrayDequeDescriptor(dataSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: ArrayDeque<E>) {
        val size = value.size
        val composite = encoder.beginCollection(descriptor, size)
        val iterator = value.iterator()
        for (index in 0 until size)
            composite.encodeSerializableElement(descriptor, index, dataSerializer, iterator.next())
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): ArrayDeque<E> = merge(decoder)

    private fun readSize(decoder: CompositeDecoder, builder: ArrayList<E>): Int {
        val size = decoder.decodeCollectionSize(descriptor)
        builder.ensureCapacity(size)
        return size
    }

    private fun readElement(decoder: CompositeDecoder, index: Int, builder: ArrayList<E>, checkIndex: Boolean = true) {
        builder.add(index, decoder.decodeSerializableElement(descriptor, index, dataSerializer))
    }

    private fun readAll(decoder: CompositeDecoder, builder: ArrayList<E>, startIndex: Int, size: Int) {
        require(size >= 0) { "Size must be known in advance when using READ_ALL" }
        for (index in 0 until size)
            readElement(decoder, startIndex + index, builder, checkIndex = false)
    }
}


class ArrayDequeDescriptor(private val elementDescriptor: SerialDescriptor) : SerialDescriptor {
    override val kind: SerialKind get() = StructureKind.LIST
    override val elementsCount: Int = 1

    override fun getElementName(index: Int): String = index.toString()
    override fun getElementIndex(name: String): Int =
        name.toIntOrNull() ?: throw IllegalArgumentException("$name is not a valid list index")

    override fun isElementOptional(index: Int): Boolean {
        require(index >= 0) { "Illegal index $index, $serialName expects only non-negative indices" }
        return false
    }

    override fun getElementAnnotations(index: Int): List<Annotation> {
        require(index >= 0) { "Illegal index $index, $serialName expects only non-negative indices" }
        return emptyList()
    }

    override fun getElementDescriptor(index: Int): SerialDescriptor {
        require(index >= 0) { "Illegal index $index, $serialName expects only non-negative indices" }
        return elementDescriptor
    }

    override val serialName: String
        get() = "ArrayDequeSerializer"
}
//
//class ArrayDequeDeserializationStrategy(override val descriptor: SerialDescriptor) :
//    DeserializationStrategy<ArrayDeque<HumanBeing>> {
//    /**
//     * Deserializes the value of type [T] using the format that is represented by the given [decoder].
//     * [deserialize] method is format-agnostic and operates with a high-level structured [Decoder] API.
//     * As long as most of the formats imply an arbitrary order of properties, deserializer should be able
//     * to decode these properties in an arbitrary order and in a format-agnostic way.
//     * For that purposes, [CompositeDecoder.decodeElementIndex]-based loop is used: decoder firstly
//     * signals property at which index it is ready to decode and then expects caller to decode
//     * property with the given index.
//     *
//     * Throws [SerializationException] if value cannot be deserialized.
//     *
//     * Example of deserialize method:
//     * ```
//     * class MyData(int: Int, stringList: List<String>, alwaysZero: Long)
//     *
//     * fun deserialize(decoder: Decoder): MyData = decoder.decodeStructure(descriptor) {
//     *     // decodeStructure decodes beginning and end of the structure
//     *     var int: Int? = null
//     *     var list: List<String>? = null
//     *     loop@ while (true) {
//     *         when (val index = decodeElementIndex(descriptor)) {
//     *             DECODE_DONE -> break@loop
//     *             0 -> {
//     *                 // Decode 'int' property as Int
//     *                 int = decodeIntElement(descriptor, index = 0)
//     *             }
//     *             1 -> {
//     *                 // Decode 'stringList' property as List<String>
//     *                 list = decodeSerializableElement(descriptor, index = 1, serializer<List<String>>())
//     *             }
//     *             else -> throw SerializationException("Unexpected index $index")
//     *         }
//     *      }
//     *     if (int == null || list == null) throwMissingFieldException()
//     *     // Always use 0 as a value for alwaysZero property because we decided to do so.
//     *     return MyData(int, list, alwaysZero = 0L)
//     * }
//     * ```
//     */
//    override fun deserialize(decoder: Decoder): ArrayDeque<HumanBeing> = decoder.decodeStructure(descriptor) {
//        // decodeStructure decodes beginning and end of the structure
//        var int: Int? = null
//        var list: List<HumanBeing> = listOf<HumanBeing>()
//        loop@ while (true) {
//            when (val index = decodeElementIndex(descriptor)) {
//                DECODE_DONE -> break@loop
//                0 -> {
//                    // Decode 'int' property as Int
//                    int = decodeIntElement(descriptor, index = 0)
//                }
//                1 -> {
//                    // Decode 'stringList' property as List<String>
//                    list = decodeSerializableElement(descriptor, index = 1, serializer<List<HumanBeing>>())
//                }
//                else -> throw SerializationException("Unexpected index $index")
//            }
//        }
//        if (int == null || list == null) throwMissingFieldException()
//        // Always use 0 as a value for alwaysZero property because we decided to do so.
//        return ArrayDeque(list?.toCollection())
//    }
//
//}