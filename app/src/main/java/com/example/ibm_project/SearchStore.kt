package com.example.ibm_project

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.recent_term.*
import org.json.JSONObject
import org.jsoup.Jsoup
import java.lang.ref.WeakReference
import java.net.URL

//검색화면
// 검색한 텍스트로 api에서 (매장명 + 위도경도 + 혼잡도) 받아서 SearchedStoreListActivity로 전달

class SearchStore : AppCompatActivity() {
    lateinit var temrs:ArrayList<researchTerms>
    lateinit var termAdapter:recentTermAdapter
    lateinit var store:ArrayList<StoreData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        init()
        back.setOnClickListener {
            Toast.makeText(applicationContext,"뒤로 가기",Toast.LENGTH_SHORT).show()
            finish()
        }
        search.setOnClickListener {//돋보기 누르기 ->  SearchedStoreListActivity로 전환도어야함
            addData(searchTerms.text.toString())

            val i = intent
           // val currentloc = i.getExtra() 메인에서 위치정보 받기

            val searchKeyword = searchTerms.text.toString()
            //val urlStr ="서버 url" + searchKeyword + 위치정보
            //val task = MyAsyncTask(this)
            //task.execute(URL(urlStr))

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun removeData(pos:Int){
        termAdapter.deleteData(pos)
    }
    fun addData(terms:String){
        if(terms==""){
            return;
        }
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

    class MyAsyncTask(context: SearchStore) : AsyncTask<URL, Unit, Unit>() {
        val activityreference = WeakReference(context)
        val context = context
        val activity = activityreference.get()

        override fun doInBackground(vararg params: URL?) {
            val doc = Jsoup.connect(params[0].toString()).ignoreContentType(true).get()
            val json = JSONObject(doc.text())
            val array = json.getJSONArray("")

            for(i in 0 until array.length()){
                val address_name = array.getJSONObject(i).getString("address_name")
                val lat = array.getJSONObject(i).getString("lat")
                val lng = array.getJSONObject(i).getString("lng")
                val congestion = array.getJSONObject(i).getString("congestion")

                //여기 이미지, 전화번호, 영업중 등 추가 해서 만들기
               // val storedataterm = StoreData(storename,address_name,lat,lng,distance,null)
                //activity?.store?.add(storedataterm)
            }
        }

    }
}