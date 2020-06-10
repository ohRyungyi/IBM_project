package com.example.ibm_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mResultLocation = listOf<Address>()
    var fusedLocationClient: FusedLocationProviderClient?= null
    var locationCallback:LocationCallback?=null
    var locationRequest:LocationRequest?=null

   var currentLoc=LatLng(0.0,0.0)


    lateinit var nowlocate:String
    lateinit var nowLocation:TextView
    lateinit var adapter:locationAdapter
    lateinit var store:ArrayList<StoreData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        //load_intro()
        var loc:TextView=findViewById(R.id.locate)
        nowLocation=findViewById<TextView>(R.id.locate)
        nowlocate=nowLocation.text.toString()
        init()
        loc.setOnClickListener {
            searchPart.visibility=View.VISIBLE
            var addressList=findViewById<RecyclerView>(R.id.addressList)
            var search=findViewById<TextView>(R.id.inputAddress)
            search.setText("")
            init()
            /*var btn=findViewById<Button>(R.id.find)
            btn.setOnClickListener {
                var location=findViewById<TextView>(R.id.inputAddress)
                var txt=location.text.toString()
                var data=setData(txt)
                var adapter=locationAdapter(data)
                var list=findViewById<RecyclerView>(R.id.addressList)
                adapter.itemclicklistener=object:locationAdapter.OnItemClickListener{
                    override fun itemClick(
                        hoder: locationAdapter.MyViewHolder,
                        view: View,
                        data: String,
                        position: Int
                    ) {
                        Toast.makeText(applicationContext,data.toString()+" 위치 설정",Toast.LENGTH_SHORT).show()
                        nowlocate=data
                    }

                }
            }
            searchPart.visibility=View.INVISIBLE
            nowLocation.setText("위치 > "+nowlocate)*/
        }
        var btn=findViewById<Button>(R.id.find)
        btn.setOnClickListener {
            var location=findViewById<TextView>(R.id.inputAddress)
            var txt=location.text.toString()
            var data=setData(txt)
            adapter=locationAdapter(data)
            var list=findViewById<RecyclerView>(R.id.addressList)
            list.layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
            adapter.itemclicklistener=object:locationAdapter.OnItemClickListener{
                override fun itemClick(
                    hoder: locationAdapter.MyViewHolder,
                    view: View,
                    data: String,
                    position: Int
                ) {
                    Toast.makeText(applicationContext,data.toString()+" 위치 설정",Toast.LENGTH_SHORT).show()
                    nowlocate=data.toString()
                    searchPart.visibility=View.INVISIBLE
                    nowLocation.setText("위치 > "+data)
                    newVisitedStore()
                }

            }
            list.adapter=adapter
        }
        var searchStore=findViewById<TextView>(R.id.searchStore)
        searchStore.setOnClickListener {
            val searchStoreIntent=Intent(applicationContext,search::class.java)
            startActivity(searchStoreIntent)
        }
        var more=findViewById<TextView>(R.id.more)
        more.setOnClickListener {
            val visitedIntent=Intent(applicationContext,VisitedStoreListActivity::class.java)
            visitedIntent.putExtra("dataOfStore",store)
            startActivity(visitedIntent)

        }

    }
    fun initData():ArrayList<StoreData>{
        //어댑터에 넘겨줄 데이터 생성
        var data=ArrayList<StoreData>()
        data.add(StoreData(
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
        ))
        return data
    }
    fun newVisitedStore(){
        // 주소 변경 => 확진자 방문 매장 리스트 재설정
    }

    fun init(){
        var data=setData("")
        adapter=locationAdapter(data)
        var list=findViewById<RecyclerView>(R.id.addressList)
        list.layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        adapter.itemclicklistener=object:locationAdapter.OnItemClickListener{
            override fun itemClick(
                hoder: locationAdapter.MyViewHolder,
                view: View,
                data: String,
                position: Int
            ) {
                Toast.makeText(applicationContext,data.toString()+" 위치 설정",Toast.LENGTH_SHORT).show()
                nowlocate=data.toString()
                searchPart.visibility=View.INVISIBLE
                nowLocation.setText("위치 > "+data)
            }

        }
        list.adapter=adapter

        store=initData()

    }

    fun setData(txt:String):ArrayList<String>{
        var data=ArrayList<String>()
        if(txt==""){
            return data
        }

        data.add("서울 광진구")
        data.add("서울 광진구 능동로")
        data.add("서울 광진구 자양로")
        data.add("서울 광진구 아차산로")
        data.add("서울 광진구 광나루로")
        val size=data.size
        var index=0
        for(i in 0 until size){
            if(!data[index].contains(txt)){
                data.removeAt(index)
            }else{
                index++
            }
        }

        return data

    }

// 사용자 위치 지정하면? 그 위치 API로 전달 / 위치지정 안하면? 현재 위치 전달 -> 확진자 이용매장 이름, 위치(위도경도), 상세주소, 혼잡도 받기
// 더보기 버튼누르면 액티비티 전환

    private fun init() {

        // 지정한 위치 위도 경도 받아오기
        val mgeocorder:Geocoder = Geocoder(this)
        mResultLocation= mgeocorder.getFromLocationName("서울 광진구 능동로",1)
        val mLat = mResultLocation.get(0).latitude
        val mLng =  mResultLocation.get(0).longitude
        Log.i("geocoding",mLat.toString()+mLng.toString())

        // 더보기 버튼누르면 액티비티 전환 (확진자 이용매장이름+ 주소 + 위도경도 + 확진자방문일자 + 거리+ 혼잡도) 인텐트에 담아서 전달하기
        addtextView.setOnClickListener {
            val intro_intent = Intent(applicationContext, VisitedStoreListActivity::class.java)
            startActivity(intro_intent)
        }
    fun load_intro(){
        val intro_intent = Intent(applicationContext, intro::class.java)
        startActivity(intro_intent)
    }

        //권한체크하고 현재 위치정보 가져오기
        getCurrentLoc()
    }
    private fun getCurrentLoc() { //권한정보 체크하는 기능
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getuserlocation() //현재위치 갱신
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 100
            )
        }
    }
    private fun getuserlocation() {
        fusedLocationClient= LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient?.lastLocation?.addOnSuccessListener {//성공적으로 위치 가져왔으면?
                currentLoc = LatLng(it.latitude,it.longitude) //현재위치로 위치정보를 바꾸겠다
                Log.i("currentLocation",currentLoc.toString())
            }
    }

    override fun onRequestPermissionsResult( //권한요청하고 결과 여기로 옴
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==100){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED){ //둘다 허용되면
                getuserlocation()
            }
            else{
                Toast.makeText(this,"위치정보 제공을 하셔야 합니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

}