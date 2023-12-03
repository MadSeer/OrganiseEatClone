package com.mymind.core.base

import android.util.Log
import java.io.IOException
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject

abstract class BaseHTTPRequest {

    abstract val apikey: String
    abstract val url: String
    abstract val gettingFieldName: String
    val client = OkHttpClient()

    open fun getResponse(callback: (String) -> Unit) {
        val request = Request.Builder()
            .url(this.url)
            .addHeader("X-Api-Key", this.apikey)
            .build()

        client.newCall(request).enqueue(
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("error", "API failed", e)
                    callback("Something went wrong")
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    if (body != null) {
                        Log.v("data", body)
                    } else {
                        Log.v("data", "empty")
                    }
                    val jsonObject = JSONArray(body)
                    //val textResult = jsonObject.getJSONObject(0).getString(gettingFieldName)
                    val textResult = jsonObject.getJSONObject(0)
                    callback(textResult.toString())
                }
            }
        )
    }
}