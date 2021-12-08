package com.kel01.kantinkoperasiitdel.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kel01.kantinkoperasiitdel.R
import com.kel01.kantinkoperasiitdel.fragment.LoginFragment
import com.kel01.kantinkoperasiitdel.fragment.DashboardFragment
import com.kel01.kantinkoperasiitdel.fragment.HomeFragment
import com.kel01.kantinkoperasiitdel.fragment.RegisterFragment

class MainActivity : AppCompatActivity() {
    private val fragmentHome: Fragment = HomeFragment()
    private val fragmentLogin: Fragment = LoginFragment()
    private val fragmentRegister: Fragment = RegisterFragment()
    private val fragmentDashboard: Fragment = DashboardFragment()
    private val fragmentManager: FragmentManager = supportFragmentManager
    private var active: Fragment = fragmentHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNav()
    }

    fun setupBottomNav() {
        fragmentManager.beginTransaction().add(R.id.frameLayoutContainer, fragmentHome).show(fragmentHome).commit()
        fragmentManager.beginTransaction().add(R.id.frameLayoutContainer, fragmentDashboard).hide(fragmentDashboard).commit()
        fragmentManager.beginTransaction().add(R.id.frameLayoutContainer, fragmentLogin).hide(fragmentLogin).commit()
        fragmentManager.beginTransaction().add(R.id.frameLayoutContainer, fragmentRegister).hide(fragmentRegister).commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when(item.itemId) {
                R.id.navigation_home -> {
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_dashboard -> {
                    callFragment(1, fragmentDashboard)
                }
                R.id.navigation_akun -> {
                    callFragment(2, fragmentLogin)
                }
            }

            false

        }
    }

    fun callFragment(index: Int, fragment: Fragment) {
        menuItem = menu.getItem(index)
        menuItem.isChecked = true
        fragmentManager.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}