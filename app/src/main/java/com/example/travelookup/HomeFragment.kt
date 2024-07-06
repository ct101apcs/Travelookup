package com.example.travelookup

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var searchBar: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transportBookingButton: ImageView = view.findViewById(R.id.transport_booking)
        val searchButton: ImageButton = view.findViewById(R.id.searchButton)
        searchBar = view.findViewById(R.id.search_bar)

        transportBookingButton.setOnClickListener{
            Toast.makeText(context, "Transport booking", Toast.LENGTH_SHORT).show()
            startActivity(Intent(getActivity(), TransportBookingActivity::class.java))}

        searchButton.setOnClickListener{
            onSearchButtonClick()
        }

        searchBar.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                onSearchButtonClick()
                true
            } else {
                false
            }
        }

        searchBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearchButtonClick()
                true
            } else {
                false
            }
        }
    }

    private fun onSearchButtonClick() {
        Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()

        val searchText = searchBar.text.toString()
        if (searchText == "") {
            Toast.makeText(context, "Please enter a search term", Toast.LENGTH_SHORT).show()
        } else {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Announcement")
            builder.setMessage("Results of " + searchText)
            builder.setPositiveButton(android.R.string.ok) { dialog, which ->
                Toast.makeText(
                    context,
                    android.R.string.yes, Toast.LENGTH_SHORT
                ).show()
            }

            builder.setNegativeButton(android.R.string.cancel) { dialog, which ->
                Toast.makeText(
                    context,
                    android.R.string.no, Toast.LENGTH_SHORT
                ).show()
            }
            builder.show()
        }
    }
}
