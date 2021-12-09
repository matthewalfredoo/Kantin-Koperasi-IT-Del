package com.kel01.kantinkoperasiitdel.activity

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.kel01.kantinkoperasiitdel.R
import com.kel01.kantinkoperasiitdel.api.ApiClient
import com.kel01.kantinkoperasiitdel.model.RegisterResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var textViewRegister: TextView
    private lateinit var editTextNoKTP: EditText
    private lateinit var editTextNamaLengkap: EditText
    private lateinit var editTextNoHP: EditText
    private lateinit var editTextUsernameEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        textViewRegister = findViewById(R.id.textViewRegister)
        editTextNoKTP = findViewById(R.id.editTextNoKTPRegister)
        editTextNamaLengkap = findViewById(R.id.editTextNamaLengkapRegister)
        editTextNoHP = findViewById(R.id.editTextNoHPRegister)
        editTextUsernameEmail = findViewById(R.id.editTextUsernameEmailRegister)
        editTextPassword = findViewById(R.id.editTextPasswordRegister)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v != null) {
            when(v.id) {
                R.id.btnRegister -> {
                    checkRegister()
                }
            }
        }
    }

    fun checkRegister() {
        val inputNoKTP = editTextNoKTP.text.toString().trim()
        val inputNamaLengkap = editTextNamaLengkap.text.toString().trim()
        val inputNoHP = editTextNoHP.text.toString().trim()
        val inputUsernameEmail = editTextUsernameEmail.text.toString().trim()
        val inputPassword = editTextPassword.text.toString()

        var emptyField = false

        if(inputNoKTP.isEmpty()) {
            emptyField = true
            setError(editTextNoKTP)
        }
        if(inputNamaLengkap.isEmpty()) {
            emptyField = true
            setError(editTextNamaLengkap)
        }
        if(inputNoHP.isEmpty()) {
            emptyField = true
            setError(editTextNoHP)
        }
        if(inputUsernameEmail.isEmpty()) {
            emptyField = true
            setError(editTextUsernameEmail)
        }
        if(inputPassword.isEmpty()) {
            emptyField = true
            setError(editTextPassword)
        }

        if(!emptyField) {
            sendRegister(inputNoKTP, inputNamaLengkap, inputNoHP, inputUsernameEmail, inputPassword)
        }
    }

    fun setError(editText: EditText){
        editText.error = "Field tidak boleh kosong"
    }

    fun sendRegister(
        inputNoKTP: String,
        inputNamaLengkap: String,
        inputNoHP: String,
        inputUsernameEmail: String,
        inputPassword: String
    ) {
        var success: Int? = 1
        var message: String? = ""

        ApiClient
            .getClient
            .register(inputNoKTP, inputNamaLengkap, inputNoHP, inputUsernameEmail, inputPassword)
            .enqueue(object : Callback<RegisterResponseModel> {
                override fun onResponse(
                    call: Call<RegisterResponseModel>,
                    response: Response<RegisterResponseModel>
                ) {
                    success = response.body()?.success
                    message = response.body()?.message.toString()

                    if(success == 1) {
                        Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_SHORT).show()
                    }
                    if(success == 0) {
                        val builder = AlertDialog.Builder(this@RegisterActivity)
                        with(builder) {
                            setTitle("Gagal")
                            setIcon(R.drawable.ic_baseline_warning_24)
                            setMessage(message)
                            setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
//                                Toast.makeText(applicationContext, "OK", Toast.LENGTH_SHORT).show()
                            })
                            show()
                        }
                    }
                }

                override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
                    t.message?.let { Log.d("DEBUG", it) }
                }

            })

    }
}