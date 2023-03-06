package com.example.witrans.model
import java.util.Date

data class Order(
    val _id:String,
    val fname:String,
    val sname:String,
    val email:String,
    val address:String,
    val companyname:String,
    val phonenumber:String,
    val lplate:String,
    val fin:String,
    val model:String,
    val pickup: Pickup,
    val destination: Destination,
    val status:String,
    val username:String,
    val manufecturer:String,
    val timeStemp:Date,
    val distance:Double,
    val duration:String,
    val ownerFirstname:String,
    val ownerSurname:String,
    val driver:String,
    val orderNumber:String
    ){
    constructor() : this("0",
        "","","",
        "","","",
        "","","",
        Pickup(), Destination(),
        "","","",
        Date(),
        0.0,"","","","",""
    )
}