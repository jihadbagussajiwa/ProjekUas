package com.example.futsalbookapp

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.futsalbookapp.models.Profile_model
import com.example.futsalbookapp.ui.profile.ProfileFragment

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.circle_photo
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.*



class Profile : AppCompatActivity() {

    lateinit var rg_gender :RadioGroup
    lateinit var rb_male :RadioButton
    lateinit var rb_female :RadioButton
//    lateinit var txt_user: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btn_select_photo.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
        btn_save.setOnClickListener{
            uploadImageToFirebase()
        }




    }




    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode ==0 && resultCode == Activity.RESULT_OK && data != null){
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            circle_photo.setImageBitmap(bitmap)


//            val bitMapDrawable = BitmapDrawable(bitmap)
//            btn_select_photo.setBackgroundDrawable(bitMapDrawable)
        }
    }
    private fun uploadImageToFirebase() {
        if (selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref =  FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("Upload Image", "Upload url :${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d("get url", "File Location :${ref.downloadUrl}")
                }
                saveProfileToDatabase(it.toString())
            }

    }
    private fun saveProfileToDatabase(photo_url:String) {
        val uid = FirebaseAuth.getInstance().uid
        val email = FirebaseAuth.getInstance().currentUser?.email
        val username = edit_username.text
        val hp = edit_nohp.text
        rg_gender = findViewById(R.id.radioGroup) as RadioGroup
        rb_male = findViewById(R.id.rb_male) as RadioButton
        rb_female = findViewById(R.id.rb_female) as RadioButton

        var gen = " "
        if(rg_gender.checkedRadioButtonId != -1){
            if (rb_male.isChecked){
                gen +="Pria"
            }
            else if(rb_female.isChecked){
                gen +="Wanita"
            }
        }
        val ref = FirebaseDatabase.getInstance().getReference("/profile/$uid")

        val prof = Profile_model(uid.toString(), photo_url,username.toString(),hp.toString(),gen)
        ref.setValue(prof)


        Toast.makeText(applicationContext,"Data Berhasil Disimpan",Toast.LENGTH_LONG).show()
//        val x = Intent(this, ProfileFragment::class.java)
//        startActivity(x)
    }
    fun loadProfileData() {
        val uid = FirebaseAuth.getInstance().uid

        val ref = FirebaseDatabase.getInstance().getReference("/profile/$uid")
        val postListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val y = p0.getValue(Profile_model::class.java)
                val x = y?.username
                txt_user.text = x.toString()
                Log.d("Usrname=",x.toString())
            }

            override fun onCancelled(p0: DatabaseError) {
            }


        }
        ref.addValueEventListener(postListener)
}
}