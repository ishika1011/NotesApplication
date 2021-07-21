package com.example.practical10

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_user_details.*
import java.text.DateFormat
import java.util.*


class userDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        heart.setImageResource(R.drawable.heartanim)
        val anim: AnimationDrawable = heart.drawable as AnimationDrawable
        anim.start()

        textView33.setOnClickListener(View.OnClickListener {
                            // Get Current Date
                val c = Calendar.getInstance()
                val mYear: Int
                val mMonth: Int
                val mDay: Int

                mYear = c[Calendar.YEAR]
                mMonth = c[Calendar.MONTH]
                mDay = c[Calendar.DAY_OF_MONTH]
                val datePickerDialog = DatePickerDialog(
                    this,
                    { view, year, monthOfYear, dayOfMonth -> textView33.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year },
                    mYear,
                    mMonth,
                    mDay
                )
                datePickerDialog.show()
        })
        textView25.setOnClickListener(View.OnClickListener {
            var mHour: Int
            var mMinute: Int
//                 Get Current Time
                val c = Calendar.getInstance()
                mHour = c[Calendar.HOUR_OF_DAY]
                mMinute = c[Calendar.MINUTE]

                // Launch Time Picker Dialog
                val timePickerDialog = TimePickerDialog(
                    this,
                    { _, hourOfDay, minute -> textView33.text = textView33.text.toString()+" : "+"$hourOfDay:$minute" },

                    mHour,
                    mMinute,
                    false
                )
                timePickerDialog.show()
        })

        imageView4.setImageResource(R.drawable.gallaryanim)
        val anim1: AnimationDrawable = imageView4.drawable as AnimationDrawable
        anim1.start()
        val currentDateTimeString = DateFormat.getDateTimeInstance().format(Date())
        textView25.text = currentDateTimeString

        botnav1.selectedItemId=R.id.dashboard
        botnav1.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.dashboard -> {
                    return@OnNavigationItemSelectedListener false
                }
                R.id.note -> {
                    val intent = Intent(this, Addnotes::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

        var tv14=findViewById<TextView>(R.id.textView14)
        var tv15=findViewById<TextView>(R.id.textView15)
        var tv17=findViewById<TextView>(R.id.textView17)
        var tv19=findViewById<TextView>(R.id.textView19)
        var tv8=findViewById<TextView>(R.id.textView8)
        var tv9=findViewById<TextView>(R.id.textView9)

            tv8.setText(LoginInfo.name).toString()
            tv9.setText(LoginInfo.mail).toString()
            tv14.setText(LoginInfo.name)
            tv15.setText("+91" + LoginInfo.number)
            tv17.setText(LoginInfo.city)
            tv19.setText(LoginInfo.mail)


        var logout = findViewById<TextView>(R.id.textView10)
        logout.setOnClickListener(View.OnClickListener {
            LoginInfo.name = ""
            LoginInfo.password = ""
            LoginInfo.mail = ""
            LoginInfo.number = ""
            LoginInfo.city = ""
            LoginInfo.login = false
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        })
    }
}