package com.example.kltn.payment

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kltn.R
import org.json.JSONException
import org.json.JSONObject


class ConfirmationActivity : AppCompatActivity() {
    lateinit var textViewId: TextView
    lateinit var textViewStatus:TextView
    lateinit var textViewAmount: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
        textViewId  = findViewById(R.id.paymentId)
        textViewStatus = findViewById(R.id.paymentStatus)
        textViewAmount= findViewById(R.id.paymentAmount)
        val intent = intent


        try {
            val jsonDetails = JSONObject(intent.getStringExtra("PaymentDetails"))
                val rs = jsonDetails.getJSONObject("response")
                Toast.makeText(this,rs.toString(),Toast.LENGTH_LONG).show()
                textViewId.text = rs.getString("id")
                textViewStatus.text = rs.getString("state")
                val paymentAmount = intent.getStringExtra("PaymentAmount")
                textViewAmount.text = paymentAmount
        } catch (e: JSONException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}