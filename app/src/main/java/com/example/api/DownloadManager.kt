package com.example.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class DownloadManager {
    companion object {
        suspend fun send(mainActivity: MainActivityViewModel?) {

            val client = OkHttpClient()
            val url = "https://restcountries.eu/rest/v2/all"
            val request = Request.Builder()
                    .url(url)
                    .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    Log.e("GetAllCountries", call.toString())

                }

                override fun onResponse(call: Call, response: Response) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val bodyInString = response.body?.string()
                        bodyInString?.let {
                            Log.w("GetAllCountries", bodyInString)
                            //val JsonObject = JSONObject(bodyInString)
                            val json = JSONArray(bodyInString)
                            //val results = JsonObject.optJSONArray("results")
                            json.let {
                                Log.w("GetAllCountries", it.toString())
                                val gson = Gson()

                                val itemType = object : TypeToken<List<Country>>() {}.type

                                val list = gson.fromJson<List<Country>>(it.toString(), itemType)

                                mainActivity?.getApiResults(list)
                            }
                        }
                    }
                }
            })
        }

    }
}