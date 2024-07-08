package com.example.travelookup
import Flight
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelookup.R

class FlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val originAirportCode: TextView = itemView.findViewById(R.id.original_airport_code)
    val originAirportCity: TextView = itemView.findViewById(R.id.original_airport_city)
    val destinationAirportCode: TextView = itemView.findViewById(R.id.destination_airport_code)
    val destinationAirportCity: TextView = itemView.findViewById(R.id.destination_airport_city)
    val flightDate: TextView = itemView.findViewById(R.id.flight_date)
    val departureTime: TextView = itemView.findViewById(R.id.departure_time)
    val price: TextView = itemView.findViewById(R.id.price)
    val flightNumber: TextView = itemView.findViewById(R.id.flight_number)

fun bind(flight: Flight) {
        originAirportCode.text = flight.origin.code.toString()
        originAirportCity.text = flight.origin.city.toString()
        destinationAirportCode.text = flight.destination.code.toString()
        destinationAirportCity.text = flight.destination.city.toString()
        departureTime.text = flight.departureTime.toString()
        flightNumber.text = flight.flightNumber.toString()
    }
}
