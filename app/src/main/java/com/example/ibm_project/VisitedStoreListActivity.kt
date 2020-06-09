package com.example.ibm_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// 확진자가 방문한 매장 리스트화면
// 인텐트로 메인액티비티에서 (확진자 이용매장이름+ 주소 + 위도경도 + 확진자방문일자 + 거리) 받기 -> 리사이클러뷰에 띄우기
// 리사이클러뷰에 클릭리스너 달아서 클릭하면 인텐트 만들어서 MapActivity로 전달 (인텐트에 (확진자 이용매장이름+ 주소 + 위도경도 + 확진자방문일자 +혼잡도 +거리) 담기)

class VisitedStoreListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visited_store_list)
    }
}