package driver

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
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
            encodeBooleanElement(descriptor, 2, value.realHero!!)
            encodeBooleanElement(descriptor, 3, value.hasToothPick)
            encodeLongElement(descriptor, 4, value.impactSeed)
            encodeStringElement(descriptor, 5, value.soundtrackName)
            encodeDoubleElement(descriptor, 6, value.minutesOfWaiting)
            encodeSerializableElement(descriptor, 7, Mood.serializer(), value.mood)
            encodeSerializableElement(descriptor, 8, Car.serializer(), value.car)
            encodeSerializableElement(descriptor, 9, Instant.serializer(), value.creationDate)
            encodeLongElement(descriptor, 10, value.id!!)
        }
    }


    override fun deserialize(decoder: Decoder): HumanBeing = decoder.decodeStructure(descriptor) {
        // decodeStructure decodes beginning and end of the structure
        var name: String = "defaultName"
        var coordinates: Coordinates = Coordinates(0, 0)
        var realHero: Boolean? = false
        var hasToothPick: Boolean = false
        var impactSeed: Long = 0L
        var soundtrackName: String = "defaultSoundtrackName"
        var minutesOfWaiting: Double = 0.0
        var mood: Mood = Mood.APATHY
        var car: Car = Car("defaultCarName")
        var creationDate: Instant = Clock.System.now()
        var id: Long? = null
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
                9 -> {
                    creationDate = decodeSerializableElement(descriptor, index = 9, Instant.serializer())
                }
                10 -> {
                    id = decodeLongElement(descriptor, index = 10)
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
            car,
            creationDate,
            id
        )
    }

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("HumanBeing") {
            element<String>("Name")
            element<Coordinates>("Coordinates")
            element<Boolean>("Real Hero")
            element<Boolean>("Has Toothpick")
            element<Long>("Impact Seed")
            element<String>("Soundtrack Name")
            element<Double>("Minutes of Waiting")
            element<Mood>("Mood")
            element<Car>("Car")
            element<Instant>("Creation Date")
            element<Long>("ID")
        }
}