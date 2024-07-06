package com.example.travelookup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transportBookingButton: ImageView = view.findViewById(R.id.transport_booking)

        transportBookingButton.setOnClickListener{
            Toast.makeText(context, "Transport booking", Toast.LENGTH_SHORT).show()
            startActivity(Intent(getActivity(), TransportBookingActivity::class.java))}
    }
}