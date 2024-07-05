package com.example.travelookup

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView

class PersonalInfoEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_info_edit)

        val backButton:ImageButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val avatar : ShapeableImageView = findViewById(R.id.profile_avatar)
        val changeAvatarButton : FloatingActionButton = findViewById(R.id.change_avatar_button)

        changeAvatarButton.setOnClickListener {
            // Open gallery
        }
    }
}