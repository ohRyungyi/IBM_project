package com.example.ibm_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_search.*

// 확진자가 방문한 매장 리스트화면
// 인텐트로 메인액티비티에서 (확진자 이용매장이름+ 주소 + 위도경도 + 확진자방문일자 + 거리) 받기 -> 리사이클러뷰에 띄우기
// 리사이클러뷰에 클릭리스너 달아서 클릭하면 인텐트 만들어서 MapActivity로 전달 (인텐트에 (확진자 이용매장이름+ 주소 + 위도경도 + 확진자방문일자 +혼잡도 +거리) 담기)

class VisitedStoreListActivity : AppCompatActivity() {
    lateinit var visitedAdapter:VisitiedStoreAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visited_store_list)
        back.setOnClickListener {
            onBackPressed()
        }
        init()
    }

    fun init() {
        var intent=intent
        var data=initData(intent.extras)

        visitedAdapter=VisitiedStoreAdapter(data)
        var storelist=findViewById<RecyclerView>(R.id.visitedStoreRecycler)
        storelist.layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        visitedAdapter.onitemclick=object:VisitiedStoreAdapter.OnItemClickListener{
            override fun itemclick(
                viewHolder: VisitiedStoreAdapter.MyViewHolder,
                view: View,
                data: StoreData,
                position: Int
            ) {
                Toast.makeText(applicationContext,data.name, Toast.LENGTH_SHORT).show()
            }

        }
        storelist.adapter=visitedAdapter
    }

    fun initData(input:Bundle?):ArrayList<StoreData>{
        //어댑터에 넘겨줄 데이터 생성
        var data=ArrayList<StoreData>()
        /*data.add(StoreData(
            "아름다운 카페",
            "서울 광진구 OO로 ㅁㅁ길",
            36.656f,
            128.457f,
            500.0f,
            -1,
            2,
            ""
        ))
        data.add(StoreData(
            "담백한 고기집",
            "서울 광진구 OO로 ㅁㅁ길",
            36.656f,
            128.457f,
            500.0f,
            -1,
            2,
            ""
        ))
        data.add(StoreData(
            "분위기 카페",
            "서울 광진구 OO로 ㅁㅁ길",
            36.656f,
            128.457f,
            900.0f,
            -1,
            5,
            ""
        ))
        data.add(StoreData(
            "유명한 의류매장",
            "서울 광진구 OO로 ㅁㅁ길",
            36.656f,
            128.457f,
            1000.0f,
            1,
            7,
            ""
        ))
        data.add(StoreData(
            "고급진 카페",
            "서울 광진구 OO로 ㅁㅁ길",
            36.656f,
            128.457f,
            1300.0f,
            1,
            9,
            ""
        ))*/
        return data
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}