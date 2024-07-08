package com.example.travelookup
import Flight
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelookup.R

class FlightAdapter(private val flightList: List<Flight>) : RecyclerView.Adapter<FlightViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flight_layout_card, parent, false)
        return FlightViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val flight = flightList[position]
        holder.originAirportCode.text = flight.origin.code.toString()
        holder.originAirportCity.text = flight.origin.city.toString()
        holder.destinationAirportCode.text = flight.destination.code.toString()
        holder.destinationAirportCity.text = flight.destination.city.toString()
        holder.departureTime.text = flight.departureTime.toString().substring(0,10)
        holder.flightNumber.text = flight.flightNumber.toString()
    }

    override fun getItemCount() = flightList.size
}
