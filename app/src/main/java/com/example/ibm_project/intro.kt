package com.example.ibm_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class intro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        Handler().postDelayed({
            //loading
            finish()
        },2000)
    }


}