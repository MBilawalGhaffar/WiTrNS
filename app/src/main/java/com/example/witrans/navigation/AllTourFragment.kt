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
import com.example.witrans.adapter.AllToursAdapter
import com.example.witrans.adapter.CurrentToursAdapter
import com.example.witrans.databinding.FragmentAllTourBinding


class AllTourFragment : Fragment() {
    private lateinit var binding: FragmentAllTourBinding
    private var mContext:Context?=null
    private lateinit var allToursAdapter: AllToursAdapter
    private lateinit var allTourRecyclerView: RecyclerView
    private lateinit var allTourSwipeRefreshLayout: SwipeRefreshLayout

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAllTourBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allTourSwipeRefreshLayout=binding.allTourRefreshSwipe
        allTourRecyclerView=binding.allTourRecyclerView
        allTourRecyclerView.layoutManager= LinearLayoutManager(mContext)
        allToursAdapter= AllToursAdapter(mContext!!){

        }
        allTourRecyclerView.adapter=allToursAdapter


        allTourSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext!!,R.color.colorPrimary))
        allTourSwipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.myLooper()!!).postDelayed({
                allTourSwipeRefreshLayout.isRefreshing=false
            },2000)
        }
    }


}