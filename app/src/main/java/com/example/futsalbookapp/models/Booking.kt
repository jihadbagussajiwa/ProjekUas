package com.example.futsalbookapp.models

class Booking ( val book_id: String, val user_id: String, val tanggal: String, val jam: String, val lama:String, val lap:String, val harga:String, val bonus:String){
    constructor() : this("","","","","","","","")
}
