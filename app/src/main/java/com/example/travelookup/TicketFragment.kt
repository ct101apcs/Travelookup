package com.example.travelookup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TicketFragment : Fragment(R.layout.fragment_ticket) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookingTransportCard : com.vipulasri.ticketview.TicketView = view.findViewById(R.id.booking_transport)

        bookingTransportCard.setOnClickListener {
            startActivity(Intent(getActivity(), TransportBookingActivity::class.java))
        }
    }
}