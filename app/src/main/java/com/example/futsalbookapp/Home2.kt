package com.example.futsalbookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class Home2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        if(intent != null)
//            text_logged_email.text = intent.getStringExtra("email")



    }
    private fun signOut() {

        FirebaseAuth.getInstance().currentUser == null

        val intent = Intent(this@Home2, MainActivity::class.java)
        intent.flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

        finish()
    }


}



