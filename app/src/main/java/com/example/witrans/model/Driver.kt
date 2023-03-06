package com.example.witrans.model


data class Driver(
    val _id:String,
    val username:String,
    val driverId:String,
    val dob:String,
    val email:String,
    val phoneNumber:String,
    val address:String,
    val name:String,
    val password:String
    ){
    constructor() : this("","","","","","","","","")
}