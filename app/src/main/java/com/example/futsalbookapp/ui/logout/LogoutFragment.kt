package com.example.futsalbookapp.ui.logout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.futsalbookapp.R
import com.example.futsalbookapp.ui.records.RecordsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_records.view.*

class LogoutFragment : Fragment() {

    private lateinit var logoutViewModel: LogoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logoutViewModel =
            ViewModelProviders.of(this).get(LogoutViewModel::class.java)


        val root = inflater.inflate(R.layout.fragment_logout, container, false)

//        val textView: TextView = root.findViewById(R.id.text_slideshow)
        logoutViewModel.text.observe(this, Observer {
            //            textView.text = it
        })


        return root

    }

}