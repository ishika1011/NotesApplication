package com.example.practical10

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.widget.Toast

class AlarmServices : BroadcastReceiver() {
    var NOTIFICATION_ID = "notification-id"
    var NOTIFICATION = "notification"
    var channelId="com.example.practical9"
    var descriptiona="Test notify"

    override fun onReceive(context: Context?, intent: Intent?) {

        val mp:MediaPlayer?
        if(intent!=null && context!=null) {

            if (intent.getStringExtra("alarm") == "mp3") {
                Toast.makeText(context, "Alarm fired", Toast.LENGTH_LONG).show()
                mp = MediaPlayer.create(context, R.raw.alarm_beeps)
                mp?.start()

            }else if(intent.getStringExtra("noti")=="notification"){

                val notification: Notification? = intent.getParcelableExtra(NOTIFICATION)
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val notificationChannel = NotificationChannel(
                        channelId,
                        descriptiona,
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    notificationManager.createNotificationChannel(notificationChannel)
                }
                val id = intent.getIntExtra(NOTIFICATION_ID, 0)
                assert(notificationManager != null)
                notificationManager.notify(id, notification)
            }
        }
    }
}

//      var note:Notes?=null
//        if(intent!=null && context!=null) {
//            var str1=intent.getStringExtra("Service1")
//            if(str1==null){ }
//            else if(str1=="Start" || str1=="Stop") {
//                var intentservices=Intent(context,playerservices::class.java)
//                intentservices.putExtra("Service1",intent.getStringExtra("Service1"))
//                if(str1=="Start1") {
//                    context.startService(intentservices)
//                }
//                else if(str1=="Stop"){
//                    context.stopService(intentservices)
//                }