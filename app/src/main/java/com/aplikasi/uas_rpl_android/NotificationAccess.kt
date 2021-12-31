package com.aplikasi.uas_rpl_android

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat



class NotificationAccess : AppCompatActivity() {
    var notification //let's me build a notification
            : NotificationCompat.Builder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_access)
        notification = NotificationCompat.Builder(this)
        notification!!.setAutoCancel(true) //clears old notifications
    }

    fun notifyButtonClicked(view: View?) {
        //build the notification
        notification!!.setSmallIcon(R.mipmap.ic_launcher)
        notification!!.setTicker("ANJAY JADI")
        notification!!.setWhen(System.currentTimeMillis())
        notification!!.setContentTitle("NOTIF Anjay")
        //notification.setContentInfo("ANJAY JADI");
        notification!!.setContentText("ANJAY JADI!")


        //where to send after clicking notification
        val intent = Intent(this, NotificationAccess::class.java)
        //allow intent from outside this activity
        //gives my phone access to the intents in this app
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        notification!!.setContentIntent(pendingIntent)

        //builds notification and sends it
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(uniqueID, notification!!.build())
    }

    companion object {
        private const val uniqueID = 45612 // random number to identify this notification
    }
}