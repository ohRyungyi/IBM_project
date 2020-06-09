package com.example.ibm_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

//첫 메인화면

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        load_intro()
        init()
    }

    private fun init() {

    }

// 사용자 위치 지정하면? 그 위치 API로 전달 / 위치지정 안하면? 현재 위치 전달 -> 확진자 이용매장 이름, 위치(위도경도), 상세주소, 혼잡도 받기
// 더보기 버튼누르면 액티비티 전환

    fun load_intro(){
        addtextView.setOnClickListener {
            val intro_intent = Intent(applicationContext, MapForSearchedActivity::class.java) //지도 넘어가는 지 확인
            startActivity(intro_intent)
        }
    }

}