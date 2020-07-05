package com.fhhy.phoenix.community.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.community.activity.FollowOrderManagerActivity
import com.fhhy.phoenix.community.activity.HighQualityTraderActivity
import com.fhhy.phoenix.community.activity.SearchTraderActivity
import com.fhhy.phoenix.community.activity.TraderInfoActivity
import com.fhhy.phoenix.community.adapter.FollowOrderAdapter
import com.fhhy.phoenix.community.contract.FollowOrderContract
import com.fhhy.phoenix.community.contract.InformationContract
import com.fhhy.phoenix.community.model.InformationModel
import com.fhhy.phoenix.community.presenter.FollowOrderPresenter
import com.fhhy.phoenix.community.presenter.InformationPresenter
import kotlinx.android.synthetic.main.fragment_follow_order.*
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.fragment_order.recyclerView
import setViewClickListener

/**
 * Created by hecuncun on 2020/7/4
 */
class FollowOrderFragment :BaseMvpFragment<FollowOrderContract.View,FollowOrderContract.Presenter>(),
    View.OnClickListener {
    private val followOrderAdapter:FollowOrderAdapter by lazy {
        FollowOrderAdapter()
    }
    override fun createPresenter(): FollowOrderContract.Presenter=FollowOrderPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_follow_order
    override fun lazyLoad() {

    }

    override fun initView(view: View) {
        super.initView(view)
        setViewClickListener(this,flSearchContainer,tv_follow_manager,tv_sort)
        initRecyclerView()
    }
    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = followOrderAdapter
            followOrderAdapter.data = mutableListOf("", "", "","","","","","")
        }
        followOrderAdapter.setOnItemClickListener { adapter, view, position ->
            startActivity(Intent(requireContext(), TraderInfoActivity::class.java))
        }
    }
    companion object{
        fun newInstance():FollowOrderFragment{
            return FollowOrderFragment()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.flSearchContainer->{
                startActivity(Intent(requireContext(),SearchTraderActivity::class.java))
            }
            R.id.tv_follow_manager->{
                startActivity(Intent(requireContext(), FollowOrderManagerActivity::class.java))
            }
            R.id.tv_sort->{
                startActivity(Intent(requireContext(), HighQualityTraderActivity::class.java))
            }
        }
    }
}