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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.witrans.R
import com.example.witrans.databinding.FragmentDriverBinding


class DriverFragment : Fragment() {
    private lateinit var binding: FragmentDriverBinding
    private var mContext:Context?=null

    private lateinit var driverSwipeRefreshLayout: SwipeRefreshLayout


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDriverBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        driverSwipeRefreshLayout=binding.driverRefreshSwipe




        driverSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext!!,R.color.colorPrimary))
        driverSwipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.myLooper()!!).postDelayed({
                driverSwipeRefreshLayout.isRefreshing=false
            },2000)
        }
    }


}