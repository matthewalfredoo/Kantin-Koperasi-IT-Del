package com.kel01.kantinkoperasiitdel.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPref (activity: Activity) {

    val login = "login"
    val role = "role"
    val myPref = "MAIN_PRF"
    val sp: SharedPreferences

    init {
        sp = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status: Boolean, role: String) {
        sp.edit().putBoolean(login, status).apply()
        sp.edit().putString(this.role, role).apply()
    }

//    fun getStatusLogin(): ArrayList<Any> {
//        var mantap = mutableMapOf<String, Any>("login" to sp.getBoolean(login, false), "role" to sp.getString(role, null))
//    }

}