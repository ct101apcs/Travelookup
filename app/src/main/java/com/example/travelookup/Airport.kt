package com.example.travelookup
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File

@Serializable
data class Location(
    val latitude: Double,
    val longitude: Double
)

@Serializable
data class Airport(
    val code: String,
    val city: String,
    val timezone: String,
    val location: Location
)

fun getAirportCitiesAndCodes(jsonString: String): List<Pair<String, String>> {
    val json = Json { ignoreUnknownKeys = true }
    val airports = json.decodeFromString<List<Airport>>(jsonString)
    return airports.map { it.city to it.code }
}
