package com.example.ibm_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_search_store_list.*

//사용자가 검색한 매장리스트 액티비티
class SearchedStoreListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_store_list)
        init()
    }

    private fun init() {
        button_map.setOnClickListener {
            val i = Intent(this,MapForSearchedActivity::class.java)
            //인텐트에 검색매장의 위도경도 매장명 상세주소 전화번호 혼잡도 담기
        }
    }
}