package com.example.kltn.payment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kltn.R
import com.example.kltn.screen.FormatData
import com.paypal.android.sdk.payments.*
import org.json.JSONException
import java.math.BigDecimal


class PaypalPaymentActivity : AppCompatActivity() {
    lateinit var buttonPay: Button
    lateinit var editTextAmount: TextView
    lateinit var paymentAmount: String
    lateinit var tvMoneyVND:TextView
    private var moneyUSD:Double = 0.0
    private lateinit var diachi:String

    companion object {
        const val PAYPAL_REQUEST_CODE: Int = 1
        val config: PayPalConfiguration =
            PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(PayPalConfig.PAYPAL_CLIENT_ID)
                .merchantName("Paypal Login")
                .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
                .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paypal_payment)
        setDialogFullScreen()
        buttonPay = findViewById(R.id.buttonPay)
        editTextAmount = findViewById(R.id.editTextAmount)
        tvMoneyVND = findViewById(R.id.tvMoneyVND)
        val intent = getIntent()
        diachi = intent.getStringExtra("diachi")
        var money = intent.getDoubleExtra("money",0.0)
        tvMoneyVND.text = FormatData.formatMoneyVND(money)
        moneyUSD = Math.round((money/23200.00)*100.00)/100.00



        editTextAmount.text = moneyUSD.toString() + " $"

        buttonPay.setOnClickListener {


            //Creating a paypalpayment
            val payment: PayPalPayment = PayPalPayment(
                BigDecimal(moneyUSD), "USD", "Payment",
                PayPalPayment.PAYMENT_INTENT_SALE
            )

            //Creating Paypal Payment activity intent
            val intent = Intent(this, PaymentActivity::class.java)
            //putting the paypal configuration to the intent
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

            //Puting paypal payment to the intent
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

            //Starting the intent activity for result
            //the request code will be used on the method onActivityResult
            startActivityForResult(intent, PAYPAL_REQUEST_CODE);
        }
    }

    override fun onDestroy() {
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                val confirm: PaymentConfirmation =
                    data!!.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION)

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        val paymentDetails = confirm.toJSONObject().toString(4)
                        Log.i("paymentExample", paymentDetails)

                        //Starting a new activity for the payment details and also putting the payment details with intent
                        startActivity(
                            Intent(this, ConfirmationActivity::class.java)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", moneyUSD)
                                .putExtra("diachi",diachi)
                        )
                    } catch (e: JSONException) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e)
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.")
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                    "paymentExample",
                    "An invalid Payment or PayPalConfiguration was submitted. Please see the docs."
                )
            }
        }
    }
    private fun setDialogFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            this.window?.statusBarColor = this.resources.getColor(R.color.colorPrimary)
            this.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}