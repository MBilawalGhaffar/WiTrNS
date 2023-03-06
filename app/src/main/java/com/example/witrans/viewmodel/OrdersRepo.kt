package com.example.witrans.viewmodel

import android.content.Context
import com.example.witrans.model.Order

class OrdersRepo {
    fun get(context: Context, id: String):MutableList<Order> {
        val list :MutableList<Order>? =ArrayList()

        return list!!
    }
}