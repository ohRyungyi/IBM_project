package com.example.ibm_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ibm_project.search
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search_store.*
import kotlinx.android.synthetic.main.recent_term.*
//검색화면
// 검색한 텍스트로 api에서 (매장명 + 위도경도 + 혼잡도) 받아서 SearchedStoreListActivity로 전달

class SearchStore : AppCompatActivity() {
    lateinit var temrs:ArrayList<researchTerms>
    lateinit var termAdapter:recentTermAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_store)
        init()
        back.setOnClickListener {
            Toast.makeText(applicationContext,"뒤로 가기",Toast.LENGTH_SHORT).show()
        }
        search.setOnClickListener {
            addData(searchTerms.text.toString())
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun removeData(pos:Int){
        termAdapter.deleteData(pos)
    }
    fun addData(terms:String){
        if(termAdapter.items.contains(researchTerms(terms))){
            termAdapter.items.remove(researchTerms(terms))
            termAdapter.items.add(0,researchTerms(terms))
        }
        termAdapter.addData(terms)
    }
    fun init(){
        var terms=ArrayList<researchTerms>()
        terms.add(researchTerms("코인노래방"))
        terms.add(researchTerms("카페"))
        terms.add(researchTerms("예쁜"))
        terms.add(researchTerms("음식점"))
        terms.add(researchTerms("음식"))

        recentTerms.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        termAdapter=recentTermAdapter(terms)
        termAdapter.itemclick=object:recentTermAdapter.OnItemClickListener{
            override fun onItemClick(
                holder: recentTermAdapter.MyViewHolder,
                view: View,
                data: researchTerms,
                position: Int
            ) {
                if(view==holder.deleteView){
                    Toast.makeText(applicationContext,data.toString()+" 검색어 삭제",Toast.LENGTH_SHORT).show()
                    removeData(position)

                }else if(view==holder.searchView){
                    Toast.makeText(applicationContext,data.toString()+" 검색 결과 찾기",Toast.LENGTH_SHORT).show()

                }
            }

        }

        recentTerms.adapter=termAdapter
    }
}