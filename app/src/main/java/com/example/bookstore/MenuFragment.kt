package com.example.bookstore

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {

    lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        signOut()
        changePassword()

        arrowBack.setOnClickListener {
            val mainActivity = Intent(context, MainActivity::class.java)
            mainActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mainActivity)
        }
    }

    private fun signOut() {

        val logOut = requireView().findViewById<TextView>(R.id.logOut)
        logOut.setOnClickListener {
            auth = FirebaseAuth.getInstance()
            auth.signOut()
            val intent = Intent(context, LoginActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)

        }


    }

    private fun  changePassword(){
        val changePass = requireView().findViewById<TextView>(R.id.changePassword)
        changePass.setOnClickListener {
            val resetActivity = Intent(context, ResetActivity::class.java)
            startActivity(resetActivity)
        }
    }

}
