package com.aplikasi.uas_rpl_android
import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BluetoothAccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth_access)

        // Declaring Button and TextView
        // 1. Changes the state of Bluetooth on button click
        // 2. Shows the state of the Bluetooth
        val btnBt = findViewById<Button>(R.id.BtBtn)
        val tvBt = findViewById<TextView>(R.id.BtTv)

        // Declaring Bluetooth adapter
        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        // On button Click
        btnBt.setOnClickListener {

            // Enable or disable the Bluetooth and display
            // the state in Text View
            if (mBluetoothAdapter.isEnabled) {
                mBluetoothAdapter.disable()
                tvBt.text = "Bluetooth is OFF"
            } else {
                mBluetoothAdapter.enable()
                tvBt.text = "Bluetooth is ON"
            }
        }
    }
}