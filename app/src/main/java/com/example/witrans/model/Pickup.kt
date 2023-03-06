package com.example.witrans.model


data class Pickup(
    val address:String,
    val date:String,
    val time:String,
    val cperson:String,
    val email:String,
    val phoneNumber:String,
    val latitude:String,
    val longitude:String,
    val note:String,
    val company:String,
    val customer:String,
    val dutyPhoto:String
    ){
    constructor() : this("","","","","","","","","","","","")
}