package com.example.witrans.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.witrans.databinding.AllTourItemViewBinding


class AllToursAdapter(private val context: Context, val onAllTourClick: () -> Unit):
    RecyclerView.Adapter<AllToursAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: AllTourItemViewBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding:AllTourItemViewBinding=AllTourItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.binding){
            this.root.setOnClickListener {
                onAllTourClick.invoke()
            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }
}