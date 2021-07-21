package com.example.practical10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/* Class to hold credentials */
class LoginInfo {
    companion object{
    var login=true
    var name = "Admin"
    var password = "123456"
    var city="admin city"
    var number="+91 "+"768173627"
    var mail="@gmail"
    }
}

class signupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var emailid = findViewById<EditText>(R.id.emailsignup)
        var passwd1 = findViewById<EditText>(R.id.pwd)
        var passwd2 = findViewById<EditText>(R.id.pwd2)
        var cityname = findViewById<EditText>(R.id.city)
        var fname = findViewById<EditText>(R.id.fullname)
        var phonno = findViewById<EditText>(R.id.pn)

        var signupbtn = findViewById<Button>(R.id.button2)
        signupbtn.setOnClickListener(View.OnClickListener {
            LoginInfo.name=fname.text.toString()
            LoginInfo.password=passwd1.text.toString()
            LoginInfo.mail=emailid.text.toString()
            LoginInfo.number=phonno.text.toString()
            LoginInfo.city=cityname.text.toString()

            if(emailid.toString().isEmpty() || cityname.toString().isEmpty() || fname.toString().isEmpty())
            {
                emailid?.error ="require"
                cityname?.error ="require"
                fname?.error ="require"
            }
            if(passwd2.text.toString()==passwd1.text.toString())
            {
                if(passwd2.length()>=8) {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    passwd1?.error ="enter more than 8 char"
                }
            }
            else
            {
                passwd1?.error ="password not same or empty"
            }
        })
    }
}
