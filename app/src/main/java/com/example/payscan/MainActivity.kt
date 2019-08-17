package com.example.payscan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import android.R.string
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
//import org.junit.experimental.results.ResultMatchers.isSuccessful
import java.io.IOException
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import android.os.StrictMode
import android.util.JsonReader
import com.beust.klaxon.Klaxon
import org.json.JSONObject


class Person(val email: String)
class Auth(val token:String)

class MainActivity : AppCompatActivity() {

    val client = OkHttpClient()
    val JSON = "application/json; charset=utf-8".toMediaType()
    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val intent = Intent(this, home::class.java)
        intent.putExtra("EXIT", true)


        startActivity(intent)
        val login_button = findViewById<Button>(R.id.login_btn)
        login_button.setOnClickListener {
            val emailField = findViewById<EditText>(R.id.emailfield)
            val emailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailField.text).matches()
            val passwordField = findViewById<EditText>(R.id.password_field)



            val url = "http://68.183.159.53:8000/api/users/create-user/"



            val client = OkHttpClient()

            val JSON = "application/json; charset=utf-8".toMediaType()
            val body = "{\"email\":\"${emailField.text}\",\"password\":\"${passwordField.text}}\"}".toRequestBody(JSON)
            val request = Request.Builder()
                .url(url.toString())
                .post(body)
                .build()


            val  response = client . newCall (request).execute()

            println(response.request)
            val resp = response.body!!.string()


            if (resp!="{}") {
                val result = Klaxon()
                    .parse<Person>(resp)


                val email = result?.email
                val url ="http://68.183.159.53:8000/api/token-auth/"
                val JSON = "application/json; charset=utf-8".toMediaType()
                val body = "{\"username\":\"$email\",\"password\":\"${passwordField.text}}\"}".toRequestBody(JSON)
                val request = Request.Builder()
                    .url(url.toString())
                    .post(body)
                    .build()


                val  response = client . newCall (request).execute()

                val auth_result = Klaxon()
                    .parse<Auth>(response.body!!.string())
                val token = auth_result?.token


                    val intent = Intent(this, home::class.java)
                    intent.putExtra("EXIT", true)
                intent.putExtra("token", token)
                    startActivity(intent)

            }else{
                val url ="http://68.183.159.53:8000/api/token-auth/"

                val JSON = "application/json; charset=utf-8".toMediaType()
                val body = "{\"username\":\"${emailField.text}\",\"password\":\"${passwordField.text}}\"}".toRequestBody(JSON)
                val request = Request.Builder()
                    .url(url.toString())
                    .post(body)
                    .build()


                val  response = client . newCall (request).execute()

                println(response.request)
                val resp = response.body!!.string()
                val auth_result = Klaxon()
                    .parse<Auth>(response.body!!.string())
                val token = auth_result?.token

                val intent = Intent(this, home::class.java)
                intent.putExtra("EXIT", true)
                intent.putExtra("token", token)
                startActivity(intent)


            }














        }


    }





}
