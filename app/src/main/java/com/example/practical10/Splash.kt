package com.example.practical10

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_user_details.*

class Splash : AppCompatActivity(), Animation.AnimationListener {

    lateinit var logoanim:AnimationDrawable
    lateinit var animrotate:Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
            statusBarColor= Color.TRANSPARENT
        }

        imageView7.setImageResource(R.drawable.logoanim)
        logoanim=imageView7.drawable as AnimationDrawable

        animrotate=AnimationUtils.loadAnimation(this,R.anim.rotatelogoanim)
        animrotate.setAnimationListener(this)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus){
            logoanim.start()
            imageView7.startAnimation(animrotate)
        }else{
              logoanim.stop()
        }
    }

    override fun onAnimationStart(p0: Animation?) {

    }

    override fun onAnimationEnd(p0: Animation?) {
        overridePendingTransition(R.anim.scale_in,R.anim.scale_out)
        var intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onAnimationRepeat(p0: Animation?) {

    }
}