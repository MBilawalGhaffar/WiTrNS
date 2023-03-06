package com.example.witrans.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.witrans.model.Order

class OrderViewModel(private val ordersRepo: OrdersRepo):ViewModel() {
    var ordersList:MutableLiveData<MutableList<Order>> =MutableLiveData()
    fun getOrders(context: Context,id:String){
        ordersList.value=ordersRepo.get(context,id)
    }
}