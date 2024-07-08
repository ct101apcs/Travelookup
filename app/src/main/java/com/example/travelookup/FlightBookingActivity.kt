package com.example.travelookup

import Flight
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahana.horizontalcalendar.HorizontalCalendar
import com.sahana.horizontalcalendar.OnDateSelectListener
import getAllFlightsInDay

class FlightBookingActivity : AppCompatActivity() {
    private lateinit var mHorizontalCalendar: HorizontalCalendar
    private lateinit var mDateTextView: TextView
    private lateinit var mBackButton: ImageButton
    private var flightDataFile: String = ""
    private lateinit var mflightListRecyclerView: RecyclerView
    private lateinit var flightAdapter: FlightAdapter
    private var suitableFlights: List<Flight> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transport_flight)

        val originLocation = intent.getStringExtra("originLocation")
        val destinationLocation = intent.getStringExtra("destinationLocation")

        mBackButton = findViewById<ImageButton>(R.id.flight_back_button)
        mHorizontalCalendar = findViewById(R.id.horizontal_calendar)
        mDateTextView = findViewById(R.id.date_text_view)
        mflightListRecyclerView = findViewById(R.id.flight_list_recycler_view)

        mBackButton.setOnClickListener {
            finish()
        }

        // Set up RecyclerView
        mflightListRecyclerView.layoutManager = LinearLayoutManager(this)
        flightAdapter = FlightAdapter(suitableFlights)
        mflightListRecyclerView.adapter = flightAdapter

        mHorizontalCalendar.setOnDateSelectListener(OnDateSelectListener { dateModel ->
            mDateTextView.text = if (dateModel != null) {
                "${dateModel.day} ${dateModel.dayOfWeek} ${dateModel.month}, ${dateModel.year}"
            } else ""

            flightDataFile = "${dateModel.year}_${dateModel.month}_${dateModel.day}.json"

            val flightJsonString = AppUtils.readJSONFromAssets(this, flightDataFile)
            val flightList = getAllFlightsInDay(flightJsonString)

            suitableFlights = flightList.filter { flight ->
                (flight.origin.city + " (" + flight.origin.code + ")") == originLocation
                        && (flight.destination.city + " (" + flight.destination.code + ")") == destinationLocation
            }

            flightAdapter = FlightAdapter(suitableFlights)
            mflightListRecyclerView.adapter = flightAdapter
        })
    }
}
