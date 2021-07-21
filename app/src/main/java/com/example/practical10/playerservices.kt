package com.example.practical10

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast

class playerservices() : Service() {

    lateinit var mediaPlayer: MediaPlayer
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(!this::mediaPlayer.isInitialized)
            mediaPlayer=MediaPlayer.create(this,R.raw.alarm_beeps)
        if(intent!=null){
            val str1 = intent!!.getStringExtra("Service1")
            Toast.makeText(applicationContext,str1,Toast.LENGTH_LONG).show()
//            if(str1=="Start")
//                mediaPlayer.start()
//            else if(str1=="Stop")
//                mediaPlayer.pause()
        }
        return START_STICKY
    }

}