package com.example.futsalbookapp.models
import com.google.firebase.database.Exclude

//class User (val username: String ="",val email :String = "", val photo :String? = null,
//            @Exclude val uid: String="")
class User ( val user_id: String,val email: String)
