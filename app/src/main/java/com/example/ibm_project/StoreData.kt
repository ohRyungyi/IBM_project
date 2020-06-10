package com.example.ibm_project

import java.io.Serializable

data class StoreData(val name:String, val address:String, val lat:Float, val lng:Float, var distance:Float, var state:Int, var daysago:Int, var image:String, var conjuction:Double,
var phone:String):Serializable {
}