package com.example.smack.Services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.smack.Model.Channel
import com.example.smack.Utilities.URL_GET_CHANNELS
import com.example.smack.Utilities.URL_REGISTER
import org.json.JSONException

object MessageService {
    val channels = ArrayList<Channel>()

    fun getChannels(context: Context?, complete: (Boolean) -> Unit) {
        val channelsRequest = object: JsonArrayRequest(Method.GET, URL_GET_CHANNELS, null, Response.Listener {response ->
            try {
                for (x in 0 until response.length()) {
                    val channel = response.getJSONObject(x)
                    var channelName = channel.getString("name")
                    var channelDescription = channel.getString("description")
                    val channelId = channel.getString("_id")

                    val newChannel  = Channel(channelName, channelDescription, channelId)
                    this.channels.add(newChannel)
                }
                complete(true)
            } catch (e: JSONException) {
                Log.d("JSON", "EXEC:" + e.localizedMessage)
                complete(false)
            }
        }, Response.ErrorListener {error ->
            Log.d("ERROR", "Could not retrieve channels: $error")
            complete(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String> ()
                headers.put("Authorization", "Bearer ${AuthService.authToken}")

                return headers
            }

        }
        Volley.newRequestQueue(context).add(channelsRequest)
    }

}