package com.aplikasi.uas_rpl_android

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.toast

class NFCAccess : AppCompatActivity() {

    private var mNfcAdapter: NfcAdapter? = null
    private var messageEditText : EditText? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfcaccess)
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        toast(NFCUtil.retrieveNFCMessage(this.intent))
    }

    override fun onResume() {
        super.onResume()
        mNfcAdapter?.let {
            NFCUtil.enableNFCInForeground(it, this, javaClass)
        }
    }

    override fun onPause() {
        super.onPause()
        mNfcAdapter?.let {
            NFCUtil.disableNFCInForeground(it, this)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val messageWrittenSuccessfully = NFCUtil.createNFCMessage(messageEditText!!.text.toString(), intent)
        toast(messageWrittenSuccessfully.ifElse("Successful Written to Tag", "Something When wrong Try Again"))
    }
}

fun <T> Boolean.ifElse(primaryResult: T, secondaryResult: T) = if (this) primaryResult else secondaryResult
