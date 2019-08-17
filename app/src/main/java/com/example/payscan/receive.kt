package com.example.payscan

import android.content.Intent.getIntent
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.beust.klaxon.Klaxon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_fragment_fragment.*
import kotlinx.android.synthetic.main.home_fragment_fragment.view.*
import kotlinx.android.synthetic.main.nav_header_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*
import kotlinx.android.synthetic.main.receive_fragment.*
import kotlinx.android.synthetic.main.receive_fragment.view.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody



class receive : Fragment() {
    val client = OkHttpClient()
    val JSON = "application/json; charset=utf-8".toMediaType()
    val picasso = Picasso.get()
    companion object {
        fun newInstance() = receive()
    }

    private lateinit var viewModel: ReceiveViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {





//            val image =BitmapFactory.decodeStream(url.openConnection().getInputStream());






//
//        image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        imageView.setImageBitmap(image);
        return inflater.inflate(R.layout.receive_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReceiveViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        class QRcodePARSE(val qr_code:String)

        val receiveamount = view.receive_amount.text
        val url = "http://68.183.159.53:8000/media/transactions/qr_codes/1566038093-ssashko.png"

        val token = activity!!.getIntent().getExtras()!!.getString("token")

        println(token)


        val qr_code = view.findViewById<ImageView>(R.id.qr_code)
        view.generate_qr.setOnClickListener { view ->
            var generate_qr_endpoint = "http://68.183.159.53:8000/api/generate-qr?qr_data="+receiveamount


            val JSON = "application/json; charset=utf-8".toMediaType()
            val request = Request.Builder()
                .url(generate_qr_endpoint)
                .build()


            val  response = client . newCall (request).execute()

            println(response.request)
            val resp = response.body!!.string()
            val qr_resp = Klaxon()
                .parse<QRcodePARSE>(resp)



                Picasso.get()
                    .load("http://68.183.159.53:8000"+qr_resp?.qr_code  )
                    .into(qr_code)

        }
    }

}
