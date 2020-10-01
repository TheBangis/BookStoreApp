package com.example.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.postDelayed
import com.example.bookstore.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_splashscreen.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.time.seconds

class Splashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val SPLASH_TIME_OUT = 3000
        Handler().postDelayed({
            //Do some stuff here, like implement deep linking

            val user = FirebaseAuth.getInstance().currentUser
            if (user != null){
                val homeIntent = Intent(this, MainActivity::class.java)
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(homeIntent)
                //finish()
            }
            else{

                val startActivity = Intent(this, StartActivity::class.java)
                startActivity(startActivity)
                finish()
            }


        }, SPLASH_TIME_OUT.toLong())



    }


}

