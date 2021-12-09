package com.kel01.kantinkoperasiitdel.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.kel01.kantinkoperasiitdel.R
import com.kel01.kantinkoperasiitdel.helper.FragmentUtil
import com.kel01.kantinkoperasiitdel.helper.SharedPref
import com.kel01.kantinkoperasiitdel.model.UserModel
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var s: SharedPref

    private lateinit var textViewNamaProfil: TextView
    private lateinit var textViewNoHPProfil: TextView
    private lateinit var textViewEmailProfil: TextView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_profil, container, false)

        s = SharedPref(this.requireActivity())

        textViewNamaProfil = view.findViewById(R.id.textViewNamaProfil)
        textViewNoHPProfil = view.findViewById(R.id.textViewNoHPProfil)
        textViewEmailProfil = view.findViewById(R.id.textViewEmailProfil)
        btnLogout = view.findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener(this)

        textViewNamaProfil.text = s.getStatusLogin().get("nama").toString()
        textViewNoHPProfil.text = s.getStatusLogin().get("noHP").toString()
        textViewEmailProfil.text = s.getStatusLogin().get("username").toString()

        return view
    }

    override fun onResume() {
        super.onResume()

        FragmentUtil.refreshFragment(this.requireActivity())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        if(v != null) {
            when(v.id) {
                R.id.btnLogout -> {
                    s.setLogout()
                }
            }
        }
    }
}