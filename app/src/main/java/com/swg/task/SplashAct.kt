package com.swg.task

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler

class SplashAct : Activity() {

    private var delayTime = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_splash)

        Handler().postDelayed( {
            startActivity(Intent(this, MainAct::class.java))
        }, delayTime.toLong())

    }

}