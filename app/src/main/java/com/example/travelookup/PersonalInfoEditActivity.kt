package com.example.travelookup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView

class PersonalInfoEditActivity : AppCompatActivity() {
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private var selectedImageUri: Uri? = null
    private lateinit var profileAvatar: ShapeableImageView
    private lateinit var changeAvatarButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_info_edit)

        profileAvatar = findViewById(R.id.profile_avatar)
        changeAvatarButton = findViewById(R.id.change_avatar_button)

        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                selectedImageUri = data?.data
                if (selectedImageUri != null) {
                    Glide.with(this).load(selectedImageUri).apply(RequestOptions.centerCropTransform()).into(profileAvatar)
                } else {
                    Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show()
                }
            }
        }

        changeAvatarButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            imagePickerLauncher.launch(intent)
        }
    }
}
