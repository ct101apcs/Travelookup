package com.example.travelookup

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class PersonalInfoEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_info_edit)

        val backButton:ImageButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }
}