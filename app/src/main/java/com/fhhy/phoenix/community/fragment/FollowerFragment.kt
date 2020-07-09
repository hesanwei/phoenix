package com.fhhy.phoenix.community.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.community.activity.TraderInfoActivity
import com.fhhy.phoenix.community.adapter.FollowerAdapter
import com.fhhy.phoenix.community.adapter.HistoricalPositionAdapter
import com.fhhy.phoenix.community.contract.FollowerContract
import com.fhhy.phoenix.community.contract.HistoricalPositionContract
import com.fhhy.phoenix.community.presenter.FollowerPresenter
import com.fhhy.phoenix.community.presenter.HistoricalPositionPresenter
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.fragment_order.*

/**
 * Created by heCunCun on 2020/7/9
 */
class FollowerFragment :BaseMvpFragment<FollowerContract.View,FollowerContract.Presenter>(),FollowerContract.View {
    private val followerAdapter by lazy {
        FollowerAdapter()
    }
    override fun createPresenter(): FollowerContract.Presenter=FollowerPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_follower

    override fun lazyLoad() {

    }

    override fun initView(view: View) {
        super.initView(view)
        initRecyclerView()
    }
    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = followerAdapter
            followerAdapter.data = mutableListOf("", "", "","","","","","")
        }
        followerAdapter.setOnItemClickListener { adapter, view, position ->
            //startActivity(Intent(requireContext(), TraderInfoActivity::class.java))
        }
    }

    companion object{
        fun newInstance():FollowerFragment{
            return FollowerFragment()
        }
    }
}