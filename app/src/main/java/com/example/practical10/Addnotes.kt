package com.example.practical10

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.android.synthetic.main.activity_note.botnav1
import kotlinx.android.synthetic.main.activity_user_details.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Addnotes : AppCompatActivity() {

    lateinit var adapter:customAdapter
    lateinit var time:String
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        imageView6.setImageResource(R.drawable.gallaryanim)
        val anim0: AnimationDrawable = imageView6.drawable as AnimationDrawable
        anim0.start()

        botnav1.selectedItemId=R.id.note
        botnav1.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.dashboard -> {
                    val intent = Intent(this, userDetails::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.note -> {
                    return@OnNavigationItemSelectedListener false
                }
            }
            false
        })


        floatingActionButton.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Add Note")
            val note=Notes(1, "UVPCE", "Ganpat University", "CE department", "10:30 Am", true,"remindertime")
            // set the custom layout
            val customLayout: View = LayoutInflater.from(this).inflate(R.layout.note_edit, null)
            customLayout.findViewById<TextInputEditText>(R.id.note_tittLe)
            customLayout.findViewById<EditText>(R.id.et1).setText(note.tittle)
            customLayout.findViewById<TextInputEditText>(R.id.note_subTitLe)
            customLayout.findViewById<EditText>(R.id.et2).setText(note.subtittle)
            customLayout.findViewById<TextInputEditText>(R.id.note_description)
            customLayout.findViewById<EditText>(R.id.et3).setText(note.description)

            val setalarm = customLayout.findViewById<Switch>(R.id.switch1)
            setalarm.isChecked=true
            val timePicker = customLayout.findViewById<TimePicker>(R.id.reminderTime)
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.hour = Calendar.HOUR
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.minute = Calendar.MINUTE
            }
            builder.setView(customLayout)

            builder.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->
                val calendar = Calendar.getInstance()
                if (Build.VERSION.SDK_INT >= 23) {
                    calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH], timePicker.hour, timePicker.minute] =
                        0
                } else {
                    calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH], timePicker.currentHour, timePicker.currentMinute] =
                        0
                }
                if (setalarm.isChecked) {
                    getNotification("1")?.let { it1 ->
                        setAlarm(
                            calendar.timeInMillis, "Start",
                            it1
                        )
                    }

                    Notes.addingarray(
                        Notes.idNote,
                        customLayout.findViewById<EditText>(R.id.et1).text.toString(),
                        customLayout.findViewById<EditText>(R.id.et2).text.toString(),
                        customLayout.findViewById<EditText>(R.id.et3).text.toString(),
                        currentDate,
                        true,
                        calendar.time.toString()
                    )

                    adapter = customAdapter(this, Notes.arrayNote)
                    userlist.adapter = adapter

                } else {
                    Toast.makeText(this, "Alarm not set", Toast.LENGTH_SHORT).show()
                }
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun setAlarm(time: Long?, str1: String, notification: Notification) {
        val intent = Intent(this, AlarmServices::class.java)
     //   intent.putExtra("alarm", "mp3")

        intent.putExtra("noti","notification")
        intent.putExtra(AlarmServices().NOTIFICATION, notification)
        val pi = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val am = getSystemService(ALARM_SERVICE) as AlarmManager
            if (time != null) {
                am.setExact(AlarmManager.RTC_WAKEUP, time, pi)
            }
        /* am.setRepeating(AlarmManager.RTC, time, AlarmManager.INTERVAL_DAY, pi); */
            Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
        getNotification("1")
    }

    private fun getNotification(content: String): Notification? {
        val builder = NotificationCompat.Builder(this, "com.example.practical9")

        builder.setContentTitle("One Plus")
        builder.setContentText("CE department")

        val notificationIntent = Intent(this, customnotification::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT)

        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
        builder.setChannelId("com.example.practical9")
        return builder.build()
    }
}





