package com.example.futsalbookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_book__succeed.*
import kotlinx.android.synthetic.main.fragment_home.*

class Book_Succeed : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book__succeed)
        txt_succeed.setOnClickListener({
            val i = Intent(this, Home::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
        })

    }
}
