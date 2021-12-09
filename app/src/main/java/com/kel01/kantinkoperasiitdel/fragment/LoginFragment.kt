package com.kel01.kantinkoperasiitdel.fragment
//
//import android.annotation.SuppressLint
//import android.app.AlertDialog
//import android.content.DialogInterface
//import android.os.Bundle
//import android.os.Handler
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.isGone
//import androidx.core.view.isInvisible
//import androidx.core.view.isVisible
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import com.kel01.kantinkoperasiitdel.R
//import com.kel01.kantinkoperasiitdel.R.*
//import com.kel01.kantinkoperasiitdel.api.ApiClient
//import com.kel01.kantinkoperasiitdel.helper.FragmentUtil
//import com.kel01.kantinkoperasiitdel.helper.SharedPref
//import com.kel01.kantinkoperasiitdel.model.LoginResponseModel
//import com.kel01.kantinkoperasiitdel.model.UserModel
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
//class LoginFragment() : Fragment(), View.OnClickListener {
//
//    // TODO Gimana caranya supaya setelah login berhasil tampilan auto ke-refresh
//
//    private var param1: String? = null
//    private var param2: String? = null
//
//    private lateinit var textViewLogin: TextView
//    private lateinit var editTextEmail: EditText
//    private lateinit var editTextPassword: EditText
//    private lateinit var btnLogin: Button
//    private lateinit var textViewMoveRegister: TextView
//
//    lateinit var s: SharedPref
//    lateinit var mHandler: Handler
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val view: View = inflater.inflate(layout.fragment_login, container, false)
//
////        mHandler = Handler()
////        mHandler.postDelayed()
//
//        s = SharedPref(this.requireActivity())
//
//        textViewLogin = view.findViewById(R.id.textViewLogin)
//        editTextEmail = view.findViewById(R.id.editTextEmailLogin)
//        editTextPassword= view.findViewById(R.id.editTextPasswordLogin)
//        btnLogin = view.findViewById(R.id.btnLogin)
//        textViewMoveRegister = view.findViewById(R.id.textViewMoveRegister)
//
//        btnLogin.setOnClickListener(this)
//        textViewMoveRegister.setOnClickListener(this)
//
//        if(s.getStatusLogin().get("login") == true) {
//            btnLogin.isVisible = false
//            refreshFragment()
//        }
//
//        return view
//
//    }
//
//    override fun onClick(v: View?) {
//        if(v != null) {
//            when(v.id) {
//                R.id.btnLogin -> {
//                    checkLogin()
//                }
//                R.id.textViewMoveRegister -> {
//                    moveFragmentRegister()
//                }
//            }
//        }
//    }
//
//    fun checkLogin() {
//        val username = editTextEmail.text.toString()
//        val password = editTextPassword.text.toString()
//        if(username.isEmpty() || password.isEmpty()) {
//            alertfail("Email dan password harus diisi.")
//        }
//        else {
//            sendLogin(username, password)
//        }
//    }
//
//    fun alertfail(s: String) {
//        //Source: https://stackoverflow.com/questions/63465960/how-to-display-a-toast-widget-in-a-fragment
//        val builder = AlertDialog.Builder(this@LoginFragment.requireActivity())
//        with(builder) {
//            setTitle("Gagal")
//            setIcon(R.drawable.ic_baseline_warning_24)
//            setMessage(s)
//            setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
//                Toast.makeText(this@LoginFragment.requireActivity(), "OK", Toast.LENGTH_SHORT).show()
//            })
//            show()
//        }
//    }
//
//    fun sendLogin(username: String, password: String) {
//        var success: Int? = 0
//        var message: String? = ""
//        var user: UserModel
//
//        ApiClient
//            .getClient
//            .login(username, password)
//            .enqueue(object : Callback<LoginResponseModel> {
//                override fun onResponse(
//                    call: Call<LoginResponseModel>,
//                    response: Response<LoginResponseModel>
//                ) {
//                    success = response.body()?.success
//                    message = response.body()?.message
//                    user = response.body()?.user!!
//
//                    if(success == 1) {
//                        Toast.makeText(this@LoginFragment.requireActivity(), message.toString(), Toast.LENGTH_SHORT).show()
//                        s.setStatusLogin(true, user.role!!, user)
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
//                    t.message?.let { Log.d("DEBUG", it) }
//                }
//
//            })
//    }
//
//    @SuppressLint("ResourceType")
//    fun moveFragmentRegister() {
//        val registerFragment: Fragment = RegisterFragment()
//        requireActivity()
//            .supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.frameLayoutContainer, registerFragment)
//            .addToBackStack(null)
//            .commit()
//    }
//
//    fun refreshFragment() {
//        FragmentUtil.refreshFragment(this.requireActivity())
//    }
//
//
//}