package com.example.kltn.screen.notification

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class FcmPush {
    var JSON = MediaType.parse("application/json; charset=utf-8")
    var url = "https://fcm.googleapis.com/fcm/send"
    var severKey =
        "AAAA32aZDzM:APA91bELQkkxdBemD3lObRm_yZdQ-_0iCC5z_1u0kOTQqOuq-4zWo17Yjs7IS7zmBrmtTZ5yhqF1iEOab6orHHzc8zVoPi6BnW4FhYpr6trrm4VAGd3mvhDPnchQB8BOCAnjxeXrq_p3"
    var gson: Gson? = null
    var okHttpClient: OkHttpClient? = null

    companion object {
        var instance = FcmPush()
    }

    init {
        gson = Gson()
        okHttpClient = OkHttpClient()
    }

    fun sendMessage(destinationUid: String, title: String, message: String) {
        val db =
            FirebaseFirestore.getInstance().collection("pushtokens").document(destinationUid).get()

        Log.d("ThangTruong", db.toString())
        db.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                var token = task?.result?.get("pushToken").toString()
                var pushModel = PushModel()
                pushModel.to = token
                pushModel.notification.title = title
                pushModel.notification.body = message

                var body = RequestBody.create(JSON, gson?.toJson(pushModel))
                var request = Request.Builder().addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "key=" + severKey)
                    .url(url).post(body).build()
                okHttpClient?.newCall(request)?.enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {
                        println(response?.body()?.string())
                    }

                })
            }
        }
    }
}