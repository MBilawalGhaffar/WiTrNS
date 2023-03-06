package com.example.witrans.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.witrans.databinding.CurrentTourItemViewBinding

class CurrentToursAdapter(private val context: Context, val onCurrentTourClick: () -> Unit):
    RecyclerView.Adapter<CurrentToursAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: CurrentTourItemViewBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding:CurrentTourItemViewBinding=CurrentTourItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.binding){
            this.root.setOnClickListener {
                onCurrentTourClick.invoke()
            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }
}