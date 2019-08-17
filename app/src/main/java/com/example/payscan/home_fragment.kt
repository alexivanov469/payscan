package com.example.payscan

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.home_fragment_fragment.view.*


class home_fragment : Fragment() {

    companion object {
        fun newInstance() = home_fragment()
    }

    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater!!.inflate(R.layout.home_fragment_fragment, container, false)

        view.receive_btn.setOnClickListener { view ->
            val fragmentManager = getFragmentManager()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            val fragmentReceive = receive()
            fragmentTransaction?.replace(R.id.fragment_placeholder, fragmentReceive)
            fragmentTransaction?.commit()
        }

        view.pay_btn.setOnClickListener { view ->
            val intent = Intent(activity, pay::class.java)
            startActivity(intent)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
