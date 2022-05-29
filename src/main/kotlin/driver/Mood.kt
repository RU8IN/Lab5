package driver;

import kotlinx.serialization.Serializable

@Serializable
public enum class Mood(val mood: String) {
    SADNESS("sadness"),
    SORROW("sorrow"),
    GLOOM("gloom"),
    APATHY("apathy"),
    FRENZY("frenzy");
}