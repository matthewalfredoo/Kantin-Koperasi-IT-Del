package com.kel01.kantinkoperasiitdel.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kel01.kantinkoperasiitdel.R
import com.kel01.kantinkoperasiitdel.R.*
import com.kel01.kantinkoperasiitdel.activity.MainActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment() : Fragment(), View.OnClickListener {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var textViewMoveRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(layout.fragment_login, container, false)

        editTextEmail = view.findViewById(R.id.editTextEmailLogin)
        editTextPassword= view.findViewById(R.id.editTextPasswordLogin)
        btnLogin = view.findViewById(R.id.btnLogin)
        textViewMoveRegister = view.findViewById(R.id.textViewMoveRegister)

        btnLogin.setOnClickListener(this)
        textViewMoveRegister.setOnClickListener(this)

        return view

    }

    override fun onClick(v: View?) {
        if(v != null) {
            when(v.id) {
                R.id.btnLogin -> {
                    checkLogin()
                }
                R.id.textViewMoveRegister -> {
                    moveFragmentRegister()
                }
            }
        }
    }

    fun checkLogin() {
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        if(email.isEmpty() || password.isEmpty()) {
            alertfail("Email dan password harus diisi.")
        }
        else {
            sendLogin()
        }
    }

    fun alertfail(s: String) {
        //Source: https://stackoverflow.com/questions/63465960/how-to-display-a-toast-widget-in-a-fragment
        val builder = AlertDialog.Builder(this@LoginFragment.requireActivity())
        with(builder) {
            setTitle("Gagal")
            setIcon(R.drawable.ic_baseline_warning_24)
            setMessage(s)
            setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(this@LoginFragment.requireActivity(), "OK", Toast.LENGTH_SHORT)
            })
            show()
        }
    }

    fun sendLogin() {

    }

    @SuppressLint("ResourceType")
    fun moveFragmentRegister() {
        val registerFragment: Fragment = RegisterFragment()
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutContainer, registerFragment)
            .addToBackStack(null)
            .commit()
    }


}