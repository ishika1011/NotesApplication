package com.example.practical10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_customnotification.*
import java.lang.Exception

class customnotification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customnotification)
    try {
        tV11.text=Notes.arrayNote[0].tittle
        tV23.text=Notes.arrayNote[0].subtittle
        tV24.text=Notes.arrayNote[0].description
        tV26.text=Notes.arrayNote[0].modificationTime
        tV28.text=Notes.arrayNote[0].remindertime
    }catch (e:Exception){
        Toast.makeText(this,"data not found",Toast.LENGTH_LONG).show()
    }

    }
}