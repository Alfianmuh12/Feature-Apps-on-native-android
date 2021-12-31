package com.aplikasi.uas_rpl_android
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btn_notification : Button
    private lateinit var btn_special : Button
    private lateinit var btn_speaker : Button
    private lateinit var btn_mic : Button
    private lateinit var btn_cam :Button
    private lateinit var btn_list : Button
    private lateinit var btn_finger : Button
    private lateinit var btn_face : Button
    private lateinit var btn_wifi : Button
    private lateinit var btn_bluetooth : Button
    private lateinit var btn_acce : Button
    private lateinit var btn_magneto : Button
    private lateinit var btn_gyro : Button
    private lateinit var btn_phone : Button
    private lateinit var btn_info : Button
    private lateinit var btn_location :Button
    private lateinit var btn_cache : Button
    private lateinit var btn_nfc : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_notification = findViewById(R.id.btn_notification)
        btn_special = findViewById(R.id.btn_special)
        btn_cam = findViewById(R.id.btn_Camera)
        btn_speaker = findViewById(R.id.btn_Speaker)
        btn_mic = findViewById(R.id.btn_Mic)
        btn_list = findViewById(R.id.btn_List)
        btn_finger= findViewById(R.id.btn_finger)
        btn_face = findViewById(R.id.btn_face)
        btn_wifi = findViewById(R.id.btn_wifi)
        btn_bluetooth = findViewById(R.id.btn_bluetooth)
        btn_acce = findViewById(R.id.btn_Acce)
        btn_magneto =  findViewById(R.id.btn_Magneto)
        btn_gyro = findViewById(R.id.btn_Gyro)
        btn_phone =  findViewById(R.id.btn_Phone)
        btn_info = findViewById(R.id.btn_imei)
        btn_location = findViewById(R.id.btn_Location)
        btn_cache = findViewById(R.id.btn_Cache)
        btn_nfc = findViewById(R.id.btn_Nfc)

        btn_cam.setOnClickListener(this)
        btn_special.setOnClickListener(this)
        btn_notification.setOnClickListener(this)
        btn_speaker.setOnClickListener(this)
        btn_mic.setOnClickListener(this)
        btn_list.setOnClickListener(this)
        btn_finger.setOnClickListener(this)
        btn_face.setOnClickListener(this)
        btn_wifi.setOnClickListener(this)
        btn_bluetooth.setOnClickListener(this)
        btn_acce.setOnClickListener(this)
        btn_magneto.setOnClickListener(this)
        btn_gyro.setOnClickListener(this)
        btn_phone.setOnClickListener(this)
        btn_info.setOnClickListener(this)
        btn_cache.setOnClickListener(this)
        btn_nfc.setOnClickListener(this)

        btn_notification.setOnClickListener{
            val intentNotify = Intent(this@MainActivity, NotificationAccess::class.java)
            startActivity(intentNotify)
        }
        btn_special.setOnClickListener{
            val intentSpecial = Intent(this@MainActivity, SpecialNotificationAccess::class.java)
            startActivity(intentSpecial)
        }
        btn_cam.setOnClickListener{
            val intentCamera = Intent(this@MainActivity, CameraAccess::class.java)
            startActivity(intentCamera)
        }
        btn_speaker.setOnClickListener{
            val intentSpeaker= Intent(this@MainActivity, SpeakerAccess::class.java)
            startActivity(intentSpeaker)
        }
        btn_mic.setOnClickListener{
            val intentMic= Intent(this@MainActivity, MicrophoneAccess::class.java)
            startActivity(intentMic)
        }
        btn_list.setOnClickListener{
            val intentList= Intent(this@MainActivity, ListBookAccess::class.java)
            startActivity(intentList)
        }
        btn_finger.setOnClickListener{
            val intentFinger= Intent(this@MainActivity, FingerpintAccess::class.java)
            startActivity(intentFinger)
        }
        btn_face.setOnClickListener{
            val intentFace= Intent(this@MainActivity, FaceDetectionAccess::class.java)
            startActivity(intentFace)
        }
        btn_nfc.setOnClickListener{
            val intentNfc= Intent(this@MainActivity, NFCAccess::class.java)
            startActivity(intentNfc)
        }
        btn_wifi.setOnClickListener{
            val intentWifi= Intent(this@MainActivity, WifiAccess::class.java)
            startActivity(intentWifi)
        }
        btn_bluetooth.setOnClickListener{
            val intentBluetooth= Intent(this@MainActivity, BluetoothAccess::class.java)
            startActivity(intentBluetooth)
        }
        btn_acce.setOnClickListener{
            val intentAcce= Intent(this@MainActivity, Accelerometer::class.java)
            startActivity(intentAcce)
        }
        btn_magneto.setOnClickListener{
            val intentMagneto = Intent(this@MainActivity, MagnetometerAccess::class.java)
            startActivity(intentMagneto)
        }
        btn_gyro.setOnClickListener{
            val intentGyro = Intent(this@MainActivity, GyroscopeAccess::class.java)
            startActivity(intentGyro)
        }
        btn_phone.setOnClickListener{
            val intentPhone = Intent(this@MainActivity, AccessCellularPhoneNumber::class.java)
            startActivity(intentPhone)
        }
        btn_info.setOnClickListener{
            val intentInfo = Intent(this@MainActivity, IMEIAccess::class.java)
            startActivity(intentInfo)
        }
        btn_location.setOnClickListener{
            val intentLocation = Intent(this@MainActivity, Location::class.java)
            startActivity(intentLocation)
        }
        btn_cache.setOnClickListener{
            val intentCache = Intent(this@MainActivity, ClearCache::class.java)
            startActivity(intentCache)
        }


    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}

