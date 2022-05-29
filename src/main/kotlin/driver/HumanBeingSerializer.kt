package driver

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

object HumanBeingSerializer : KSerializer<HumanBeing> {

    override fun serialize(encoder: Encoder, value: HumanBeing) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.name)
            encodeSerializableElement(descriptor, 1, Coordinates.serializer(), value.coordinates)
            value.realHero?.let { encodeBooleanElement(descriptor, 2, it) }
            encodeLongElement(descriptor, 3, value.impactSeed)
            encodeStringElement(descriptor, 4, value.soundtrackName)
            encodeDoubleElement(descriptor, 5, value.minutesOfWaiting)
            encodeSerializableElement(descriptor, 6, Mood.serializer(), value.mood)
            encodeSerializableElement(descriptor, 7, Car.serializer(), value.car)
        }
    }


    override fun deserialize(decoder: Decoder): HumanBeing = decoder.decodeStructure(descriptor) {
        // decodeStructure decodes beginning and end of the structure
        var name: String = "defaultName"
        var coordinates: Coordinates = Coordinates(0,0)
        var realHero: Boolean? = false
        var hasToothPick: Boolean = false
        var impactSeed: Long = 0L
        var soundtrackName: String = "defaultSoundtrackName"
        var minutesOfWaiting: Double = 0.0
        var mood: Mood = Mood.APATHY
        var car: Car = Car("defaultCarName")
        loop@ while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                DECODE_DONE -> break@loop
                0 -> {
                    name = decodeStringElement(descriptor, index = 0)
                }
                1 -> {
                    coordinates = decodeSerializableElement(descriptor, index = 1, Coordinates.serializer())
                }
                2 -> {
                    realHero = decodeBooleanElement(descriptor, index = 2)
                }
                3 -> {
                    hasToothPick = decodeBooleanElement(descriptor, index = 3)
                }
                4 -> {
                    impactSeed = decodeLongElement(descriptor, index = 4)
                }
                5 -> {
                    soundtrackName = decodeStringElement(descriptor, index = 5)
                }
                6 -> {
                    minutesOfWaiting = decodeDoubleElement(descriptor, index = 6)
                }
                7 -> {
                    mood = decodeSerializableElement(descriptor, index = 7, Mood.serializer())
                }
                8 -> {
                    car = decodeSerializableElement(descriptor, index = 8, Car.serializer())
                }
                else -> throw SerializationException("Unexpected index $index")
            }
        }
        // Always use 0 as a value for alwaysZero property because we decided to do so.
        return@decodeStructure HumanBeing(
            name,
            coordinates,
            realHero,
            hasToothPick,
            impactSeed,
            soundtrackName,
            minutesOfWaiting,
            mood,
            car
        )
    }

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("HumanBeing") {
            element<String>("name")
            element<Coordinates>("coordinates")
            element<Boolean>("realHero")
            element<Boolean>("hasToothPick")
            element<Long>("impactSeed")
            element<String>("soundtrackName")
            element<Double>("minutesOfWaiting")
            element<Mood>("mood")
            element<Car>("car")
        }

//    override val descriptor: SerialDescriptor
//        get() = PrimitiveSerialDescriptor("HumanBeing", PrimitiveKind.STRING)


}