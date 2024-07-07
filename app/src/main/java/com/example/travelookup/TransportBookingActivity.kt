package com.example.travelookup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TransportBookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transport_booking)

        val bookingSearchButton: Button = findViewById(R.id.booking_search_button)
        val backButton: ImageButton = findViewById(R.id.transport_back_button)

        bookingSearchButton.setOnClickListener {
            Toast.makeText(this, "Search for booking", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, FlightBookingActivity::class.java))
            finish()
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
