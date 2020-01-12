package com.example.futsalbookapp.models

class Profile_model(val user_id: String,val photo_url: String, val username: String, val no_hp:String, val gender: String){
    constructor():this("","","","",""){}
}

