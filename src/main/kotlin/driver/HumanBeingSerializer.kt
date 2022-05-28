package driver

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure

object HumanBeingSerializer : KSerializer<HumanBeing> {
    override fun deserialize(decoder: Decoder): HumanBeing =
        decoder.decodeStructure(this.descriptor) {
            val array = Array<Any?>(this@HumanBeingSerializer.descriptor.elementsCount) { null }
            loop@while (true) {
                when (val index = decodeElementIndex(this@HumanBeingSerializer.descriptor)) {
                    0 -> array[0] = decodeStringElement(this@HumanBeingSerializer.descriptor, 0)
                    1 -> array[1] = decodeIntElement(this@HumanBeingSerializer.descriptor, 1)
                    2 -> array[2] = decodeIntElement(this@HumanBeingSerializer.descriptor, 2)
                    3 -> array[3] = decodeBooleanElement(this@HumanBeingSerializer.descriptor, 3)
                    4 -> array[4] = decodeBooleanElement(this@HumanBeingSerializer.descriptor, 4)
                    5 -> array[5] = decodeLongElement(this@HumanBeingSerializer.descriptor, 5)
                    6 -> array[6] = decodeStringElement(this@HumanBeingSerializer.descriptor, 6)
                    7 -> array[7] = decodeDoubleElement(this@HumanBeingSerializer.descriptor, 7)
                    8 -> array[8] =
                        decodeSerializableElement(this@HumanBeingSerializer.descriptor, 8, Mood.serializer())
                    9 -> array[9] = decodeStringElement(this@HumanBeingSerializer.descriptor, 9)
                    CompositeDecoder.DECODE_DONE -> break@loop
                    else -> throw SerializationException("Unexpected index: $index")
                }
            }
            return@decodeStructure HumanBeing(
                name = array[0] as String,
                Coordinates(array[1] as Int?, array[2] as Int?),
                realHero = array[3] as Boolean?,
                hasToothPick = array[4] as Boolean,
                impactSeed = array[5] as Long,
                soundtrackName = array[6] as String,
                minutesOfWaiting = array[7] as Double,
                mood = array[8] as Mood,
                car = Car(array[9] as String))
    }

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("HumanBeing") {
            element<String>("name")
            element<Int>("coordinates.x")
            element<Int>("coordinates.y")
            element<Boolean>("realHero")
            element<Boolean>("hasToothPick")
            element<Long>("impactSeed")
            element<String>("soundtrackName")
            element<Float>("minutesOfWaiting")
            element<Mood>("mood")
            element<String>("car.name")
        }

    override fun serialize(encoder: Encoder, value: HumanBeing) {
        TODO("Not yet implemented")
    }
}