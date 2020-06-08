package com.example.ibm_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recomended_store_list.*

//추천매장 리스트
class RecomendedStoreListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomended_store_list)
        init()
    }

    private fun init() {
        /*button2.setOnClickListener {
            val i = Intent(this,MapActivity::class.java)
            // i.putExtra로 추천매장 위도경도, 매장명, 상세주소, 전화번호, 혼잡도 담기
        }*/
    }
}