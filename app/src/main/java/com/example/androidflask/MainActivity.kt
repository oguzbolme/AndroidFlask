package com.example.androidflask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = editText_name.text
        val surname = editText_surname.text

        button_post.setOnClickListener {
            val jsonObj = JsonObject()
            jsonObj.addProperty("name",name.toString())
            jsonObj.addProperty("surname",surname.toString())

            API.service.postName(jsonObj).enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            println("POST Throwable EXCEPTION:: " + t.message)
                        }
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                val msg = response.body()?.string()
                                println("POST msg from server :: " + msg)
                                Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
                            }
                        }
                    })
        }

        button_get.setOnClickListener { API.service.getHello(name.toString()).enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            println("Throwable EXCEPTION:: " + t.message)
                        }
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                val msg = response.body()?.string()
                                println("GET msg from server :: " + msg)
                                Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
                            }
                        }
                    })
        }
    }
}

