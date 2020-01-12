package com.example.futsalbookapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.futsalbookapp.Book
import com.example.futsalbookapp.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        root.btn_book1.setOnClickListener {
            val Home = Intent(context, Book::class.java)
            startActivity(Home)
        }
        root.wa_btn.setOnClickListener {
            val intent = Intent( Intent.ACTION_VIEW,
                Uri.parse("https://api.whatsapp.com/send?phone=6282244445828&text=Halo%20mau%20putsal%20gan"))
            startActivity(intent)
        }
        root.ig_btn.setOnClickListener {
            val intent = Intent( Intent.ACTION_VIEW,
                Uri.parse("https://www.instagram.com/p/B6VilOxpGX5/?igshid=bvpqjulq4lla"))
            startActivity(intent)
        }
        root.wa_btn.setOnClickListener {
            val intent = Intent( Intent.ACTION_VIEW,
                Uri.parse("https://api.whatsapp.com/send?phone=6282244445828&text=Halo%20mau%20putsal%20gan"))
            startActivity(intent)
        }
        root.fb_btn.setOnClickListener {
            val intent = Intent( Intent.ACTION_VIEW,
                Uri.parse("https://m.facebook.com/futsal.cok"))
            startActivity(intent)
        }
        return root

}
    fun instagram(){
        ig_btn.setOnClickListener({
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/?hl=id&gl=ID"))
            startActivity(i)
        })

    }
}