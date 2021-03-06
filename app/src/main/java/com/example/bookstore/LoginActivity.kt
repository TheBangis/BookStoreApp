 package com.example.bookstore

import android.app.ActivityManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

            logIn()
            signHere()
            passwordReset()


    }

    private fun logIn() {

        loginButton.setOnClickListener {

            auth = FirebaseAuth.getInstance()

            emailEditText = findViewById(R.id.editEmail)
            passwordEditText = findViewById(R.id.editPassword)

            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (emailEditText.text.toString().isEmpty()){
                emailEditText.error = "Email required"
                emailEditText.isFocusable = true
            }
            else if (passwordEditText.text.toString().isEmpty()){

                passwordEditText.error = "Password required"
                passwordEditText.isFocusable = true
            }
            else{



            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {

                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                            finish()
//                            progressBar.visibility = View.VISIBLE
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()


                        } else {
                            Toast.makeText(
                                this,
                                "Failed to logged in Network error",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    }
        }
    }

    private fun signHere(){
        signUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun passwordReset(){

        forgetPassword.setOnClickListener {

            val intent = Intent(this, ResetActivity::class.java)
            startActivity(intent)
        }
    }

}
