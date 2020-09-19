//package com.memes.memedia

//import android.content.Intent
//import android.os.Bundle
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.Fragment
//import com.memes.memedia.ui.home.HomeFragment
//import com.memes.memedia.ui.profile.ProfileFragment
//import com.memes.memedia.ui.search.SearchFragment


//class MainActivity : AppCompatActivity()  {

//    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.home -> {
//                    moveToFragment(HomeFragment())
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.search -> {
//                    moveToFragment(SearchFragment())
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.profile -> {
//                    moveToFragment(ProfileFragment())
//                    return@OnNavigationItemSelectedListener true
//                }
//            }
//
//            false
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//
//
//        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
//
//        moveToFragment(HomeFragment())
//    }
//
//    private fun moveToFragment(fragment: Fragment) {
//        val fragmentTrans = supportFragmentManager.beginTransaction()
//        fragmentTrans.replace(R.id.fragment_container, fragment)
//        fragmentTrans.commit()
//    }

//}
