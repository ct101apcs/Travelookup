package com.example.travelookup

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.snackbar.Snackbar

class PersonalInfoEditActivity : AppCompatActivity() {
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private var selectedImageUri: Uri? = null
    private lateinit var profileAvatar: ShapeableImageView
    private lateinit var changeAvatarButton: FloatingActionButton
    private lateinit var saveButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var fullName: TextView

    private lateinit var sharedPref: SharedPreferences
    private var firstName: String = ""
    private var lastName: String = ""
    private var phone: String = ""
    private var email: String = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_info_edit)

        profileAvatar = findViewById(R.id.edit_profile_avatar)
        changeAvatarButton = findViewById(R.id.change_avatar_button)
        saveButton = findViewById(R.id.save_changes_button)
        backButton = findViewById(R.id.edit_back_button)
        fullName = findViewById(R.id.full_name)

        val firstNameText = findViewById<EditText>(R.id.first_name_edit_text)
        val lastNameText = findViewById<EditText>(R.id.last_name_edit_text)
        val phoneText = findViewById<EditText>(R.id.phone_edit_text)
        val emailText = findViewById<EditText>(R.id.email_edit_text)

        sharedPref = getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)

        firstNameText.setText(sharedPref.getString(AppUtils.FIRST_NAME_KEY, ""))
        lastNameText.setText(sharedPref.getString(AppUtils.LAST_NAME_KEY, ""))
        phoneText.setText(sharedPref.getString(AppUtils.PHONE_KEY, ""))
        emailText.setText(sharedPref.getString(AppUtils.EMAIL_KEY, ""))
        selectedImageUri = Uri.parse(sharedPref.getString(AppUtils.PROFILE_AVATAR, ""))
        Glide.with(this).load(sharedPref.getString(AppUtils.PROFILE_AVATAR, "")).apply(RequestOptions.centerCropTransform()).into(profileAvatar)

        fullName.text = "${sharedPref.getString(AppUtils.FIRST_NAME_KEY, "")} ${sharedPref.getString(AppUtils.LAST_NAME_KEY, "")}"

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

        saveButton.setOnClickListener {
            firstName= firstNameText.text.toString()
            lastName = lastNameText.text.toString()
            phone = phoneText.text.toString()
            email = emailText.text.toString()

            when{
                TextUtils.isEmpty(firstName) -> Snackbar.make(it, "Please input your first name", Snackbar.LENGTH_SHORT).show()
                TextUtils.isEmpty(lastName) -> Snackbar.make(it, "Please input your last name", Snackbar.LENGTH_SHORT).show()
                TextUtils.isEmpty(phone) -> Snackbar.make(it, "Please input your phone number", Snackbar.LENGTH_SHORT).show()
                TextUtils.isEmpty(email) -> Snackbar.make(it, "Please input your email", Snackbar.LENGTH_SHORT).show()
                else -> {
                    val editor = sharedPref.edit()
                    editor.putString(AppUtils.FIRST_NAME_KEY, firstName)
                    editor.putString(AppUtils.LAST_NAME_KEY, lastName)
                    editor.putString(AppUtils.PHONE_KEY, phone)
                    editor.putString(AppUtils.EMAIL_KEY, email)
                    editor.putString(AppUtils.PROFILE_AVATAR, selectedImageUri.toString())
                    editor.apply()

                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    imagePickerLauncher.launch(intent)

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
