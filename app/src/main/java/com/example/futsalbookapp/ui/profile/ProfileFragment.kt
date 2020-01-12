package com.example.futsalbookapp.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.futsalbookapp.R
import com.example.futsalbookapp.models.Profile_model
import com.example.futsalbookapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var mDatabase :DatabaseReference
    lateinit var prof :User



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val uid = FirebaseAuth.getInstance().uid
        val gen = FirebaseDatabase.getInstance()



        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        loadProfileData()

        mDatabase = FirebaseDatabase.getInstance().reference


        return root
    }
    private fun loadProfileData() {
        val uid = FirebaseAuth.getInstance().uid
        val email = FirebaseAuth.getInstance().currentUser?.email

        val ref = FirebaseDatabase.getInstance().getReference("/profile/$uid")
        val postListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

                val y = p0.getValue(Profile_model::class.java)

                val us = y?.username
                val em = email.toString()
                val gen = y?.gender
                val hp = y?.no_hp
                val pic = y?.photo_url
                Log.d("url photo ",pic)

//                Picasso.get().load(pic).into(circle1_photo)

                txt_user.text = us.toString()
                txt_mail.text = em
                txt_gend.text = gen.toString()
                txt_hp.text = hp.toString()



            }


            override fun onCancelled(p0: DatabaseError) {
            }

        }
        ref.addValueEventListener(postListener)
    }



}

