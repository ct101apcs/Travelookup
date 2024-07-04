package com.example.travelookup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personalInfoText: TextView = view.findViewById(R.id.personal_in4_text)

        personalInfoText.setOnClickListener {
            Toast.makeText(context, "Edit personal info", Toast.LENGTH_SHORT).show()
            startActivity(Intent(getActivity(), PersonalInfoEditActivity::class.java))
        }
    }
}
