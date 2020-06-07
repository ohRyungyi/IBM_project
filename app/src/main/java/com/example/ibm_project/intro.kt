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
// 지도 버튼 누르면 API로부터 받은 확진자이용매장 위치정보+매장이름+정확한주소 어레이를 MapActivity에 넘기기

}