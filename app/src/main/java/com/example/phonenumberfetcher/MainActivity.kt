package com.example.phonenumberfetcher

import android.content.Context
import android.content.IntentSender
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.credentials.CredentialsOptions
import android.telephony.TelephonyManager
import com.google.android.gms.auth.api.credentials.Credentials
import com.google.android.gms.auth.api.credentials.HintRequest
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.credentials.Credential


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun showMobileNumberDialog() {
        val hintRequest = HintRequest.Builder()
            .setPhoneNumberIdentifierSupported(true)
            .build()

        val intent = Credentials.getClient(this).getHintPickerIntent(hintRequest)

        val intentSenderRequest = IntentSenderRequest.Builder(intent.intentSender)
            .build()
        phonePickIntentResultLauncher.launch(intentSenderRequest)
    }

    private val phonePickIntentResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result != null) {
                val intent = result.data
                val credential = intent?.getParcelableExtra<Credential>(Credential.EXTRA_KEY)
                val number = credential?.id
                Log.d(TAG, "number: $number")
            }
        }

    fun openDialog(view: View) {
        Log.d(TAG, "openDialog: ")
        showMobileNumberDialog()
    }


}