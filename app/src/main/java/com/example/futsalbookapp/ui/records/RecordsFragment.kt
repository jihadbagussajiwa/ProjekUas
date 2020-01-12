package com.example.futsalbookapp.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.futsalbookapp.R
import com.example.futsalbookapp.RecItem
import com.example.futsalbookapp.models.Booking
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.fragment_records.*
import kotlinx.android.synthetic.main.fragment_records.view.*

class RecordsFragment : Fragment() {

    private lateinit var recordsViewModel: RecordsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recordsViewModel =
            ViewModelProviders.of(this).get(RecordsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_records, container, false)
//        val textView: TextView = root.findViewById(R.id.text_slideshow)
        recordsViewModel.text.observe(this, Observer {
            //            textView.text = it
        })

        val adapter = GroupAdapter<GroupieViewHolder>()
        root.recyclerview_records.adapter = adapter



        fetchRecords()
        return root

    }
    private fun fetchRecords() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/booking/$uid")
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








