package com.kel01.kantinkoperasiitdel.fragment
//
//import android.app.AlertDialog
//import android.content.DialogInterface
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import com.kel01.kantinkoperasiitdel.R
//import com.kel01.kantinkoperasiitdel.api.ApiClient
//import com.kel01.kantinkoperasiitdel.model.RegisterResponseModel
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [RegisterFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class RegisterFragment : Fragment(), View.OnClickListener {
//
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    private lateinit var textViewRegister: TextView
//    private lateinit var editTextNoKTP: EditText
//    private lateinit var editTextNamaLengkap: EditText
//    private lateinit var editTextNoHP: EditText
//    private lateinit var editTextUsernameEmail: EditText
//    private lateinit var editTextPassword: EditText
//    private lateinit var btnRegister: Button
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
//        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
//
//        textViewRegister = view.findViewById(R.id.textViewRegister)
//        editTextNoKTP = view.findViewById(R.id.editTextNoKTPRegister)
//        editTextNamaLengkap = view.findViewById(R.id.editTextNamaLengkapRegister)
//        editTextNoHP = view.findViewById(R.id.editTextNoHPRegister)
//        editTextUsernameEmail = view.findViewById(R.id.editTextUsernameEmailRegister)
//        editTextPassword = view.findViewById(R.id.editTextPasswordRegister)
//        btnRegister = view.findViewById(R.id.btnRegister)
//
//        btnRegister.setOnClickListener(this)
//
//        return view
//
//    }
//
//    override fun onClick(v: View?) {
//        if(v != null) {
//            when(v.id) {
//                R.id.btnRegister -> {
//                    checkRegister()
//                }
//            }
//        }
//    }
//
//    fun checkRegister() {
//        val inputNoKTP = editTextNoKTP.text.toString().trim()
//        val inputNamaLengkap = editTextNamaLengkap.text.toString().trim()
//        val inputNoHP = editTextNoHP.text.toString().trim()
//        val inputUsernameEmail = editTextUsernameEmail.text.toString().trim()
//        val inputPassword = editTextPassword.text.toString()
//
//        var emptyField = false
//
//        if(inputNoKTP.isEmpty()) {
//            emptyField = true
//            setError(editTextNoKTP)
//        }
//        if(inputNamaLengkap.isEmpty()) {
//            emptyField = true
//            setError(editTextNamaLengkap)
//        }
//        if(inputNoHP.isEmpty()) {
//            emptyField = true
//            setError(editTextNoHP)
//        }
//        if(inputUsernameEmail.isEmpty()) {
//            emptyField = true
//            setError(editTextUsernameEmail)
//        }
//        if(inputPassword.isEmpty()) {
//            emptyField = true
//            setError(editTextPassword)
//        }
//
//        if(!emptyField) {
//            sendRegister(inputNoKTP, inputNamaLengkap, inputNoHP, inputUsernameEmail, inputPassword)
//        }
//    }
//
//    fun setError(editText: EditText){
//        editText.error = "Field tidak boleh kosong"
//    }
//
//    fun sendRegister(
//        inputNoKTP: String,
//        inputNamaLengkap: String,
//        inputNoHP: String,
//        inputUsernameEmail: String,
//        inputPassword: String
//    ) {
//        var success: Int? = 1
//        var message: String? = ""
//
//        ApiClient
//            .getClient
//            .register(inputNoKTP, inputNamaLengkap, inputNoHP, inputUsernameEmail, inputPassword)
//            .enqueue(object : Callback<RegisterResponseModel> {
//                override fun onResponse(
//                    call: Call<RegisterResponseModel>,
//                    response: Response<RegisterResponseModel>
//                ) {
//                    success = response.body()?.success
//                    message = response.body()?.message.toString()
//
//                    if(success == 1) {
//                        Toast.makeText(this@RegisterFragment.requireActivity(), message.toString(), Toast.LENGTH_SHORT).show()
//                    }
//                    if(success == 0) {
//                        val builder = AlertDialog.Builder(this@RegisterFragment.requireActivity())
//                        with(builder) {
//                            setTitle("Gagal")
//                            setIcon(R.drawable.ic_baseline_warning_24)
//                            setMessage(message)
//                            setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
//
//                            })
//                            show()
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
//                    t.message?.let { Log.d("DEBUG", it) }
//                }
//
//            })
//
//    }
//
//}