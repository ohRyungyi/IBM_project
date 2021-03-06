package com.example.ibm_project

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

class intro : AppCompatActivity() {

    var mResultLocation = listOf<Address>()
    var fusedLocationClient: FusedLocationProviderClient?= null
    var locationCallback: LocationCallback?=null
    var locationRequest: LocationRequest?=null

    var currentLoc=LatLng(0.0,0.0) //현재위치

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        getCurrentLoc()
        Handler().postDelayed({
            finish()
        },2000)


    }
// 지도 버튼 누르면 API로부터 받은 확진자이용매장 위치정보+매장이름+정확한주소 어레이를 MapActivity에 넘기기
//권한체크하고 현재 위치정보 가져오기

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
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED){ //둘다 허용되면
                getuserlocation()
            }
            else{
                Toast.makeText(this,"위치정보 제공을 하셔야 합니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}