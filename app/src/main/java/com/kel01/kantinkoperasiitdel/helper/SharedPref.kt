package com.kel01.kantinkoperasiitdel.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.kel01.kantinkoperasiitdel.model.UserModel

class SharedPref (activity: Activity) {

    val login = "login"
    val role = "role"
    val noKTP = "noKTP"
    val nama = "nama"
    val usernameEmail = "usernameEmail"
    val noHP = "0"
    val myPref = "MAIN_PRF"
    val sp: SharedPreferences

    init {
        sp = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status: Boolean, role: String, noKTP: String, nama: String, usernameEmail: String, noHP: String) {
        sp.edit().putBoolean(login, status).apply()
        sp.edit().putString(this.role, role).apply()
        sp.edit().putString(this.noKTP, noKTP).apply()
        sp.edit().putString(this.nama, nama).apply()
        sp.edit().putString(this.usernameEmail, usernameEmail).apply()
        sp.edit().putString(this.noHP, noHP).apply()
    }

    fun setLogout() {
        setStatusLogin(false, "", "", "", "", "")
    }

    fun getStatusLogin(): MutableMap<String, Any?> {
        var statusLogin = mutableMapOf<String, Any?>(
            "login" to sp.getBoolean(login, false),
            "role" to sp.getString(role, null),
            "noKTP" to sp.getString(noKTP, null),
            "nama" to sp.getString(nama, null),
            "username" to sp.getString(usernameEmail, null),
            "noHP" to sp.getString(noHP, null)
            )
        return statusLogin
    }

}