package com.example.futsalbookapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.futsalbookapp.models.User
import com.example.futsalbookapp.models.Booking
import com.example.futsalbookapp.models.Data
import com.example.futsalbookapp.models.Profile_model
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.alert_layout.*
import kotlinx.android.synthetic.main.alert_layout.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.*

class Book : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)

    lateinit var option : Spinner
    lateinit var option2 : Spinner
    lateinit var result : TextView
    lateinit var result1 : TextView
    var count= 0



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val options = arrayListOf("1 Jam", "2 Jam", "3 Jam")
        val options2 = arrayListOf("Lapangan 1", "Lapangan 2", "Lapangan 3")
        val price_str = "Rp "
        var price_num = 30000
        var price_num1 = 50000

        option = findViewById(R.id.spinner) as Spinner
        option2 = findViewById(R.id.spinner2) as Spinner
        result = findViewById(R.id.txt_result) as TextView
        result1 = findViewById(R.id.txt_result1) as TextView


        option.adapter = ArrayAdapter<String>(this,R.layout.color_spinner_layout,options)
        option2.adapter = ArrayAdapter<String>(this,R.layout.color_spinner_layout,options2)

        option.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text = "Pick the duration"
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                result.text = options.get(position)
                if(result1.text.equals("Lapangan 1") && position==0){
                    price_num = 30000
                    price_num.toString()
                    txt_bonus.isVisible=false
                    txt_price.text = price_str.plus(price_num)

                }
                else if(result1.text.equals("Lapangan 1") && position==1){
                    price_num  = 30000
                    price_num *= 2
                    price_num.toString()
                    txt_bonus.isVisible=false
                    txt_price.text = price_str.plus(price_num)
                }
                else if(result1.text.equals("Lapangan 1") && position==2){
                    price_num = 30000
                    price_num *= 3
                    price_num.toString()
                    val diskon = "Free 1 jam"
                    txt_bonus.text = diskon
                    txt_bonus.isVisible=true
                    txt_price.text = price_str.plus(price_num)
                }
                else if(result1.text.equals("Lapangan 2") && position==0){
                    price_num = 30000
                    txt_bonus.isVisible=false
                    price_num.toString()
                    txt_price.text = price_str.plus(price_num)
                }
                else if(result1.text.equals("Lapangan 2") && position==1){
                    price_num = 30000
                    price_num *= 2
                    txt_bonus.isVisible=false
                    price_num.toString()
                    txt_price.text = price_str.plus(price_num)
                }
                else if(result1.text.equals("Lapangan 2") && position==2){
                    price_num = 30000
                    price_num *= 3
                    val diskon = "Free 1 jam"
                    txt_bonus.text = diskon
                    txt_bonus.isVisible=true
                    price_num.toString()
                    txt_price.text = price_str.plus(price_num)
                }
                else if(result1.text.equals("Lapangan 3") && position==0){
                    price_num1 = 50000
                    txt_bonus.isVisible=false
                    price_num1.toString()
                    txt_price.text = price_str.plus(price_num)
                }
                else if(result1.text.equals("Lapangan 3") && position==1){
                    price_num1 = 50000
                    price_num1 *= 2
                    txt_bonus.isVisible=false
                    price_num1.toString()
                    txt_price.text = price_str.plus(price_num1)
                }
                else if(result1.text.equals("Lapangan 3") && position==2){
                    price_num1 = 50000
                    price_num1 *= 3
                    val diskon = "Free 1 jam"
                    txt_bonus.text = diskon
                    txt_bonus.isVisible=true
                    price_num1.toString()
                    txt_price.text = price_str.plus(price_num1)
                }
                else{
                    txt_price.text = "Price"
                }
            }
        }
        option2.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                result1.text = "Pick the field"
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                result1.text = options2.get(position)
                if(result.text.equals("1 Jam") && position==0){
                    price_num = 30000
                    txt_bonus.isVisible=false
                    price_num.toString()
                    txt_price.text = price_str.plus(price_num)

                }
                else if(result.text.equals("1 Jam") && position==1){
                    price_num  = 30000
                    txt_bonus.isVisible=false
                    price_num.toString()
                    txt_price.text = price_str.plus(price_num)
                }
                else if(result.text.equals("1 Jam") && position==2){
                    price_num1 = 50000
                    txt_bonus.isVisible=false
                    price_num1.toString()
                    txt_price.text = price_str.plus(price_num1)
                }
                else if(result.text.equals("2 Jam") && position==0){
                    price_num = 30000
                    price_num *= 2
                    txt_bonus.isVisible=false
                    price_num.toString()
                    txt_price.text = price_str.plus(price_num)
                }
                else if(result.text.equals("2 Jam") && position==1){
                    price_num = 30000
                    price_num *= 2
                    txt_bonus.isVisible=false
                    price_num.toString()
                    txt_price.text = price_str.plus(price_num)
                }
                else if(result.text.equals("2 Jam") && position==2){
                    price_num1 = 50000
                    price_num1 *= 2
                    txt_bonus.isVisible=false
                    price_num1.toString()
                    txt_price.text = price_str.plus(price_num1)
                }
                else if(result.text.equals("3 Jam") && position==0){
                    price_num = 30000
                    price_num *= 3
                    val diskon = "Free 1 jam"
                    txt_bonus.text = diskon
                    txt_bonus.isVisible=true
                    price_num1.toString()
                    txt_price.text = price_str.plus(price_num)
                }
                else if(result.text.equals("3 Jam") && position==1){
                    price_num = 30000
                    price_num *= 3
                    val diskon = "Free 1 jam"
                    txt_bonus.text = diskon
                    txt_bonus.isVisible=true
                    price_num.toString()
                    txt_price.text = price_str.plus(price_num)
                }
                else if(result.text.equals("3 Jam") && position==2){
                    price_num1 = 50000
                    price_num1 *= 3
                    val diskon = "Free 1 jam"
                    txt_bonus.text = diskon
                    txt_bonus.isVisible=true
                    price_num1.toString()
                    txt_price.text = price_str.plus(price_num1)
                }
                else{
                    txt_price.text = "Price"
                }
            }
        }
        btn_date.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view: DatePicker?, mYear: Int, mMonth: Int, mDay: Int ->
                btn_date.setText(""+mDay+"/"+mMonth+1+"/"+mYear)
            }, year,month,day)
            dpd.show()
            }

        btn_hour.setOnClickListener{
            val timeSetListener = TimePickerDialog.OnTimeSetListener{view: TimePicker?, hour: Int, minute: Int ->
                c.set(Calendar.HOUR_OF_DAY, hour)
                c.set(Calendar.MINUTE,minute)
                btn_hour.text = SimpleDateFormat("HH:mm").format(c.time)
            }
            TimePickerDialog(this, timeSetListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),true).show()
        }
    }


    fun saveUser(view:android.view.View){

//        loadProfileData()
//
        val samatgl = "10/01/2020"
        val samajam = "12:00"
        val samalap = "Lapangan 1"
        val uid = FirebaseAuth.getInstance().uid
//        val email = FirebaseAuth.getInstance().currentUser?.email
        val tgl = btn_date.text
        val jam = btn_hour.text
        val lama = txt_result.text
        val lap = txt_result1.text
        val harga = txt_price.text
        val bonus = txt_bonus.text

        val ref = FirebaseDatabase.getInstance().getReference("booking/")
        val reff = FirebaseDatabase.getInstance().getReference("/booking/data")

        val data = Data(tgl.toString(),jam.toString(),lap.toString())
        reff.setValue(data)


        val ref1 = FirebaseDatabase.getInstance().getReference().child("booking/$uid")
        val id1 = ref1.push().key




        if(btn_date.text.equals("Pick a Date")|| btn_hour.text.equals("Pick a Time")) {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.alert_layout, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()
                mDialogView.btn_alert.setOnClickListener {
                    mAlertDialog.dismiss()
                }
        }
        else {




            val postListener2 = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {

                    val m = p0.getValue(Data::class.java)

                    val tg = m?.tanggal.toString()
                    val jm = m?.jam
                    val lp = m?.lap

                    Log.d("jam",jm.toString())


//                    val data = Data(tgl.toString(),jam.toString(),lap.toString())
//                    reff.setValue(data)

                    if( lama.equals("1 Jam")){

                        val booking = Booking(id1.toString(), uid.toString(), tgl.toString(), jam.toString(), lama.toString(), lap.toString(), harga.toString(), bonus.toString())
                        ref1.child(id1.toString()).setValue(booking)
                        val book_succeed = Intent(this@Book, Book_Succeed::class.java)
                        startActivity(book_succeed)

                    }
                    else if(tgl.equals(samatgl) && jam.equals(samajam) && lap.equals(samalap)){

//                        val booking = Booking(id1.toString(), uid.toString(), tgl.toString(), jam.toString(), lama.toString(), lap.toString(), harga.toString(), bonus.toString())
//                        ref1.child(id1.toString()).setValue(booking)
//                        val book_succeed = Intent(this@Book, Book_Succeed::class.java)
//                        startActivity(book_succeed)

                        Toast.makeText(applicationContext,"Jadwal Sudah Dipesan", Toast.LENGTH_LONG).show()

                    }
                    else{

                        val booking = Booking(id1.toString(), uid.toString(), tgl.toString(), jam.toString(), lama.toString(), lap.toString(), harga.toString(), bonus.toString())
                        ref1.child(id1.toString()).setValue(booking)
                        val book_succeed = Intent(this@Book, Book_Succeed::class.java)
                        startActivity(book_succeed)
//                        Toast.makeText(applicationContext,"Jadwal Sudah Dipesan", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                }

            }
            ref.addValueEventListener(postListener2)

        }

    }
//    private fun loadProfileData() {
//        val uid = FirebaseAuth.getInstance().uid
//        val email = FirebaseAuth.getInstance().currentUser?.email
//
//        val ref = FirebaseDatabase.getInstance().getReference("/booking/data")
//        val postListener = object : ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//
//                val y = p0.getValue(Data::class.java)
//
//                val tg = y?.tanggal
//                val jm = y?.jam
//                val lp = y?.lap
//
//                Log.d("url photo ",tg.toString())
//
////                Picasso.get().load(pic).into(circle1_photo)
//
//
//            }
//
//
//            override fun onCancelled(p0: DatabaseError) {
//            }
//
//        }
//        ref.addValueEventListener(postListener)
//    }
}
