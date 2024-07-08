package com.example.travelookup

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.io.path.Path

class TransportBookingActivity : AppCompatActivity() {
    private lateinit var originLocation: String
    private lateinit var destinationLocation: String
    private lateinit var departureDateString: String
    private lateinit var returnDateString: String
    private lateinit var transportType: String
    private var passengerCount: Int = 0
    private var childCount: Int = 0
    private var petCount: Int = 0
    private var luggageCount: Int = 0

    private var rbString = "Economy or Business"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transport_booking)

        val bookingSearchButton: Button = findViewById(R.id.booking_search_button)
        val backButton: ImageButton = findViewById(R.id.transport_back_button)
        val fromLocation = findViewById<AutoCompleteTextView>(R.id.fromLocation)
        val toLocation = findViewById<AutoCompleteTextView>(R.id.toLocation)
        val swapButton = findViewById<ImageButton>(R.id.swapButton)

        val airportJsonString =  AppUtils.ReadJSONFromAssets(this, "airports.json")
        val airportList : List<Pair<String,String>> = getAirportCitiesAndCodes(airportJsonString)
        val airportNameList = mutableListOf<String>()

        airportList.forEachIndexed() { index, airport ->
            airportNameList.add(airport.first + " (" + airport.second +')')
        }

        val airportNameArray = airportNameList.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, airportNameArray)
        fromLocation.setAdapter(adapter)
        toLocation.setAdapter(adapter)

        originLocation = fromLocation.text.toString()
        destinationLocation = toLocation.text.toString()

        swapButton.setOnClickListener {
            val temp = fromLocation.text.toString()
            fromLocation.setText(toLocation.text.toString())
            toLocation.setText(temp)
        }

        bookingSearchButton.setOnClickListener {
            Toast.makeText(this, "Search for booking", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, FlightBookingActivity::class.java))
            finish()
        }

        backButton.setOnClickListener {
            finish()
        }

        setupDatePicker()
        setupClassSelection()
        setupTransportTypeSelection()
        setupPassengerDetail()
    }

    private fun setupDatePicker() {
        val departureDate = findViewById<Button>(R.id.departureDate)
        val calendar = Calendar.getInstance()

        departureDate.setOnClickListener {
            val datePicker = DatePickerDialog(
                this,
                { view, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    departureDate.text = selectedDate
                    departureDateString = selectedDate
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Set minimum date as today
            datePicker.datePicker.minDate = calendar.timeInMillis

            // Set maximum date
            val string_date = "15-April-2025"
            val f = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            try {
                val d: Date = f.parse(string_date)
                val milliseconds = d.time
                datePicker.datePicker.maxDate = milliseconds
                datePicker.show()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        val returnDate = findViewById<Button>(R.id.returnDate)
        returnDate.setOnClickListener {
            val datePicker = DatePickerDialog(
                this,
                { view, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    returnDate.text = selectedDate
                    returnDateString = selectedDate
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Set minimum date as today
            datePicker.datePicker.minDate = calendar.timeInMillis

            // Set maximum date
            val string_date = "15-April-2025"
            val f = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            try {
                val d: Date = f.parse(string_date)
                val milliseconds = d.time
                datePicker.datePicker.maxDate = milliseconds
                datePicker.show()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
    }

    private fun setupClassSelection() {
        val classSelection = findViewById<RadioGroup>(R.id.classSelection)
        classSelection.setOnCheckedChangeListener { group, checkedId ->
            val rb = findViewById<RadioButton>(checkedId)
            rbString = rb.text.toString()
        }
    }

    private fun setupTransportTypeSelection() {
        val planeType = findViewById<ImageButton>(R.id.plane_type)
        val shipType = findViewById<ImageButton>(R.id.ship_type)
        val trainType = findViewById<ImageButton>(R.id.train_type)
        val busType = findViewById<ImageButton>(R.id.bus_type)

        planeType.setOnClickListener {
            transportType = "Plane"
        }

        shipType.setOnClickListener {
            transportType = "Ship"
        }

        trainType.setOnClickListener {
            transportType = "Train"
        }

        busType.setOnClickListener {
            transportType = "Bus"
        }
    }

    private fun setupPassengerDetail(){
        val passengerCountTextView = findViewById<EditText>(R.id.passengerCount)
        val childCountTextView = findViewById<EditText>(R.id.childCount)
        val petCountTextView = findViewById<EditText>(R.id.petCount)
        val luggageCountTextView = findViewById<EditText>(R.id.luggageCount)

        val passengerCountText = passengerCountTextView.text.toString()
        val childCountText = childCountTextView.text.toString()
        val petCountText = petCountTextView.text.toString()
        val luggageCountText = luggageCountTextView.text.toString()

        when {
            passengerCountText.isEmpty() -> passengerCount = 0
            else -> passengerCount = passengerCountText.toInt()
        }

        when {
            childCountText.isEmpty() -> childCount = 0
            else -> childCount = childCountText.toInt()
        }

        when {
            petCountText.isEmpty() -> petCount = 0
            else -> petCount = petCountText.toInt()
        }

        when {
            luggageCountText.isEmpty() -> luggageCount = 0
            else -> luggageCount = luggageCountText.toInt()
        }
    }
}
