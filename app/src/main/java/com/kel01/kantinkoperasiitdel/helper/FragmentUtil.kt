package com.kel01.kantinkoperasiitdel.helper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.kel01.kantinkoperasiitdel.R

object FragmentUtil {
    fun refreshFragment(context: Context?) {
        context.let {
            val fragmentManager = (context as? AppCompatActivity)?.supportFragmentManager
            fragmentManager?.let {
                val currentFragment = fragmentManager.findFragmentById(R.id.frameLayoutContainer)
                currentFragment?.let {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.detach(it)
                    fragmentTransaction.attach(it)
                    fragmentTransaction.commit()
                }
            }
        }
    }
}