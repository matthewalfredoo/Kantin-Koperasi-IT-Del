package com.kel01.kantinkoperasiitdel.activity

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.kel01.kantinkoperasiitdel.R
import com.kel01.kantinkoperasiitdel.api.ApiClient
import com.kel01.kantinkoperasiitdel.helper.SharedPref
import com.kel01.kantinkoperasiitdel.model.LoginResponseModel
import com.kel01.kantinkoperasiitdel.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var textViewLogin: TextView
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var textViewMoveRegister: TextView

    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)

        textViewLogin = findViewById(R.id.textViewLogin)
        editTextEmail = findViewById(R.id.editTextEmailLogin)
        editTextPassword= findViewById(R.id.editTextPasswordLogin)
        btnLogin = findViewById(R.id.btnLogin)
        textViewMoveRegister = findViewById(R.id.textViewMoveRegister)

        btnLogin.setOnClickListener(this)
        textViewMoveRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v != null) {
            when(v.id) {
                R.id.btnLogin -> {
                    checkLogin()
                }
            }
        }
    }

    fun checkLogin() {
        val username = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        if(username.isEmpty() || password.isEmpty()) {
            alertfail("Email dan password harus diisi.")
        }
        else {
            sendLogin(username, password)
        }
    }

    fun alertfail(s: String) {
        //Source: https://stackoverflow.com/questions/63465960/how-to-display-a-toast-widget-in-a-fragment
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("Gagal")
            setIcon(R.drawable.ic_baseline_warning_24)
            setMessage(s)
            setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(applicationContext, "OK", Toast.LENGTH_SHORT).show()
            })
            show()
        }
    }

    fun sendLogin(username: String, password: String) {
        var success: Int? = 0
        var message: String? = ""
        var user: UserModel

        ApiClient
            .getClient
            .login(username, password)
            .enqueue(object : Callback<LoginResponseModel> {
                override fun onResponse(
                    call: Call<LoginResponseModel>,
                    response: Response<LoginResponseModel>
                ) {
                    success = response.body()?.success
                    message = response.body()?.message
                    user = response.body()?.user!!

                    if(success == 1) {
                        Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_SHORT).show()
                        s.setStatusLogin(true, user.role!!, user.noKTP!!, user.namaLengkap!!, user.username!!, user.noHP!!)
//                        textViewLogin.text = s.getStatusLogin().get("nama").toString()
                    }
                }

                override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                    t.message?.let { Log.d("DEBUG", it) }
                }

            })
    }
}