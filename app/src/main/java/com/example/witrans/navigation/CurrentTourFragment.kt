package com.example.witrans.navigation

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.witrans.R
import com.example.witrans.adapter.CurrentToursAdapter
import com.example.witrans.databinding.FragmentCurrentTourBinding


class CurrentTourFragment : Fragment() {
    private lateinit var binding: FragmentCurrentTourBinding
    private var mContext:Context?=null
    private lateinit var currentToursAdapter: CurrentToursAdapter
    private lateinit var currentTourRecyclerView: RecyclerView
    private lateinit var currentTourSwipeRefreshLayout: SwipeRefreshLayout


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCurrentTourBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentTourSwipeRefreshLayout=binding.currentTourRefreshSwipe
        currentTourRecyclerView=binding.currentTourRecyclerView
        currentTourRecyclerView.layoutManager=LinearLayoutManager(mContext)
        currentToursAdapter= CurrentToursAdapter(mContext!!){

        }
        currentTourRecyclerView.adapter=currentToursAdapter


        currentTourSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext!!,R.color.colorPrimary))
        currentTourSwipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.myLooper()!!).postDelayed({
                currentTourSwipeRefreshLayout.isRefreshing=false
            },2000)
        }


    }

}