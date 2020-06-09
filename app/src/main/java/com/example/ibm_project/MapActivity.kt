package com.example.ibm_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// 추천매장 지도+ 확진자 이용매장 지도 화면

class MapActivity : AppCompatActivity() {
    lateinit var googleMap: GoogleMap
    var loc: LatLng = LatLng(37.554752,126.970631)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        init()
        initmap()
    }

    private fun init() {

    }

    private fun initmap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapVisited) as SupportMapFragment
        mapFragment.getMapAsync{
            googleMap = it
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,16.0f))
            googleMap.setMinZoomPreference(10.0f)
            googleMap.setMaxZoomPreference(18.0f)
            val options = MarkerOptions()
            options.position(loc)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            options.title("역")
            options.title("서울역")
            val mk1 = googleMap.addMarker(options)
            mk1.showInfoWindow() //실행했을 때 보임
            initMapListener()
        }
        //맵이 다 준비되면 콜백될 함수(ONMAPREADY)를 인자로 <- sa변환으로 따로 오버라이드
    }

    private fun initMapListener() {


    }

}