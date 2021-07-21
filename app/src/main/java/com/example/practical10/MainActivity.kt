package com.example.practical10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

var userName = ""
var userPassword = ""

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var emailtext = findViewById<EditText>(R.id.email)
        var passw = findViewById<EditText>(R.id.passwd)
        var signuptv = findViewById<TextView>(R.id.textView5)

        if (userName == LoginInfo.mail && userPassword == LoginInfo.password && LoginInfo.login) {
            startActivity(Intent(this, userDetails::class.java))
        }
        else {
            signuptv.setOnClickListener(View.OnClickListener {
                val signup = Intent(this, signupActivity::class.java)
                startActivity(signup)
            })

            var loginbutton = findViewById<Button>(R.id.button)
            loginbutton.setOnClickListener(View.OnClickListener {
                userName = emailtext.getText().toString()
                userPassword = passw.getText().toString()

                if (userName.isEmpty() || userPassword.isEmpty()) {
                    emailtext?.error = "enter your register mail"
                    passw?.error = "enter correct password"
                } else if (userName == LoginInfo.mail && userPassword == LoginInfo.password) {
                    startActivity(Intent(this, userDetails::class.java))
                } else {
                    Toast.makeText(this, "Please enter name and password!", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }
}