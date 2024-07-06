package com.example.travelookup

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sahana.horizontalcalendar.HorizontalCalendar
import com.sahana.horizontalcalendar.OnDateSelectListener


class FlightBookingActivity : AppCompatActivity(){
    private lateinit var mHorizontalCalendar: HorizontalCalendar
    private lateinit var mDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transport_flight)

//        mHorizontalCalendar.setOnDateSelectListener(OnDateSelectListener { dateModel ->
//            mDateTextView.setText(
//                if (dateModel != null) dateModel.day.toString() + " " + dateModel.dayOfWeek + " " + dateModel.month + "," + dateModel.year else ""
//            )
//        })
    }
}