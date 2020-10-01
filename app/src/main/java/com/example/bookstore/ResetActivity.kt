package com.example.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_reset.*

class ResetActivity : AppCompatActivity() {

    lateinit private var emailEditText: TextInputLayout
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)

        resetButton.setOnClickListener {

            auth = FirebaseAuth.getInstance()

            emailEditText = findViewById(R.id.editEmail)

            resetEmail()

        }

    }

    private  fun resetEmail(){

        val emailAddress = emailEditText.editText?.text.toString()

        auth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Email sent to " + emailAddress, Toast.LENGTH_LONG).show()
                }

                else{

                    Toast.makeText(this, "fails", Toast.LENGTH_LONG).show()

                }
            }

    }


}
