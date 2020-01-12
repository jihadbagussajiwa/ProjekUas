package com.example.futsalbookapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futsalbookapp.models.Booking
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_book.view.*
import kotlinx.android.synthetic.main.activity_records.*
import kotlinx.android.synthetic.main.fragment_records.*
import kotlinx.android.synthetic.main.records_layout.view.*

class Records : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerview1_records.adapter = adapter






//        fetchRecords()
    }

    private fun fetchRecords() {
        val ref = FirebaseDatabase.getInstance().getReference("/booking")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()
                p0.children.forEach{
                    val books = it.getValue(Booking::class.java)
                    if(books!=null){
                        adapter.add(RecItem(books))

                    }
                }
                recyclerview_records.adapter = adapter

            }


            override fun onCancelled(p0: DatabaseError) {

            }



        })
    }
}
class RecItem(val rec:Booking): Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.record_kode.text = rec.book_id
        viewHolder.itemView.record_tgl.text = rec.tanggal
        viewHolder.itemView.record_jam.text = rec.jam
        viewHolder.itemView.record_lama.text = rec.lama
        viewHolder.itemView.record_lap.text = rec.lap
        viewHolder.itemView.record_hrg.text = rec.harga
        viewHolder.itemView.record_bon.text = rec.bonus
    }

    override fun getLayout(): Int {
        return R.layout.records_layout
    }
}




