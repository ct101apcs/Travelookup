import com.example.travelookup.Airport
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File


@Serializable
data class Duration(
    val locale: String,
    val hours: Int,
    val minutes: Int
)

@Serializable
data class PassengerCapacity(
    val total: Int,
    val main: Int,
    val first: Int
)

@Serializable
data class Aircraft(
    val model: String,
    val passengerCapacity: PassengerCapacity,
    val speed: Int
)

@Serializable
data class Flight(
    val flightNumber: String,
    val origin: Airport,
    val destination: Airport,
    val distance: Int,
    val duration: Duration,
    val departureTime: String,
    val arrivalTime: String,
    val aircraft: Aircraft
)

fun getAllFlightsInDay(jsonString: String): List<Flight> {
    if (jsonString.isEmpty()) {
        return emptyList()
    }
    val json = Json { ignoreUnknownKeys = true }
    val flights = json.decodeFromString<List<Flight>>(jsonString)
    return flights
}

