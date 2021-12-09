package com.kel01.kantinkoperasiitdel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kel01.kantinkoperasiitdel.R
import com.kel01.kantinkoperasiitdel.helper.FragmentUtil
import com.kel01.kantinkoperasiitdel.helper.SharedPref

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment() : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var textViewHome: TextView

    lateinit var s: SharedPref

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

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        s = SharedPref(this.requireActivity())

        textViewHome = view.findViewById(R.id.text_home)

        if(s.getStatusLogin().get("login") == true) {
            textViewHome.text = "LOGIN UDHA BOS"
            FragmentUtil.refreshFragment(this.requireActivity())
        }

        return view

    }

    override fun onResume() {
        super.onResume()

        if(s.getStatusLogin().get("login") == true) {
            textViewHome.text = "LOGIN UDHA BOS"
            FragmentUtil.refreshFragment(this.requireActivity())
        }

//        if(s.getStatusLogin().get("login") == false || s.getStatusLogin().get("login") == null){
//            textViewHome.text = "Home"
//            FragmentUtil.refreshFragment(this.requireActivity())
//        }
    }

    override fun onPause() {
        super.onPause()

        if(s.getStatusLogin().get("login") == true) {
            textViewHome.text = "LOGIN UDHA BOS"
            FragmentUtil.refreshFragment(this.requireActivity())
        }
    }

}