package com.example.futsalbookapp

import android.os.Bundle

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.ContextMenu

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.futsalbookapp.models.User
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseError

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.nav_header_main2.view.*


class Home : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var mGoogleApiClient: GoogleApiClient
    private lateinit var mDatabase : DatabaseReference
    lateinit var firebaseAuth :FirebaseAuth

    internal lateinit var viewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val uid = FirebaseAuth.getInstance().uid
        val email = FirebaseAuth.getInstance().currentUser?.email
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val user = User(uid.toString(),email.toString())
        ref.setValue(user)



        val headerView = nav_view.getHeaderView(0)
        headerView.text_logged_email.text = email.toString()


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
//            firebaseAuth.signOut()
            FirebaseAuth.getInstance().signOut()
            Auth.GoogleSignInApi.signOut(mGoogleApiClient)
            finish()

        }




        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.



        mDatabase = FirebaseDatabase.getInstance().reference
//        ProfileData()


        appBarConfiguration = AppBarConfiguration(

            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_records,
                R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_main2_drawer, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



    fun instagram(view: View){
        ig_btn.setOnClickListener({
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/p/B6VilOxpGX5/?igshid=bvpqjulq4lla"))
            startActivity(i)
        })

    }
    fun facebook(view: View){
        fb_btn.setOnClickListener({
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/futsal.cok"))
            startActivity(i)
        })

    }
    fun wa(view: View){
        wa_btn.setOnClickListener({
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6282244445828&text=Halo%20mau%20putsal%20gan"))
            startActivity(i)
        })

    }
    fun book(view:View){
        btn_book1.setOnClickListener({
            val Home = Intent(this, Book::class.java)
            startActivity(Home)
        })
    }
    fun profile(view:View){
        btn_edit_prof.setOnClickListener({
            val Profile = Intent(this, Profile::class.java)
            startActivity(Profile)
        })
    }



}
