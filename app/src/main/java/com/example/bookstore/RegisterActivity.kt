package com.example.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit private var emailEditText: EditText
    lateinit private var passwordEditText: EditText
    lateinit private var nameEditText: EditText
    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase
    lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        signUpTx.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        registerButton.setOnClickListener {

            database = FirebaseDatabase.getInstance()
            databaseReference = database.reference.child("Users")
            auth = FirebaseAuth.getInstance()
            nameEditText = findViewById(R.id.editName)
            emailEditText = findViewById(R.id.editEmail)
            passwordEditText = findViewById(R.id.editPassword)

            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                    if(it.isSuccessful){

                        val userId = auth!!.currentUser!!.uid
                        var currentUserDb = databaseReference!!.child(userId)

                        currentUserDb.child("name").setValue(name)
                        currentUserDb.child("email").setValue(email)
                        currentUserDb.child("password").setValue(password)

                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)

                        progressBar.visibility = View.VISIBLE
                        Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show()
                        verifyEmail()
                    }

                    else{

                        Toast.makeText(this, "Registration failed", Toast.LENGTH_LONG).show()
                    }

                }

            else{
                Toast.makeText(this, "All field are  required", Toast.LENGTH_LONG).show()

            }
        }

    }

    private fun verifyEmail(){
        val user = auth.currentUser
        user!!.sendEmailVerification()
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    Toast.makeText(this, "Verification email sent to " + user.email, Toast.LENGTH_SHORT).show()
                }


            }
    }


}
