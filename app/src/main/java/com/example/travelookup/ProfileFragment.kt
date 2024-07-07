package com.example.travelookup

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var profileAvatar: ShapeableImageView
    private lateinit var fullName: TextView

    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personalInfoText: TextView = view.findViewById(R.id.personal_in4_text)
        fullName = view.findViewById(R.id.profile_full_name)
        sharedPref = requireActivity().getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)

//        <-- Set avatar and full name-->
        profileAvatar = view.findViewById(R.id.profile_avatar)
        Glide.with(this).load(sharedPref.getString(AppUtils.PROFILE_AVATAR, "")).into(profileAvatar)

        fullName.text = "${sharedPref.getString(AppUtils.FIRST_NAME_KEY, "")} ${sharedPref.getString(AppUtils.LAST_NAME_KEY, "")}"

        personalInfoText.setOnClickListener {
            Toast.makeText(context, "Edit personal info", Toast.LENGTH_SHORT).show()
            startActivity(Intent(getActivity(), PersonalInfoEditActivity::class.java))
        }
    }
}
