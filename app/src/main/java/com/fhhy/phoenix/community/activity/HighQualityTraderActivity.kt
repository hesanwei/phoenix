package com.fhhy.phoenix.community.activity

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.community.adapter.FollowOrderAdapter
import com.fhhy.phoenix.community.contract.HighQualityTraderContract
import com.fhhy.phoenix.community.presenter.HighQualityTraderPresenter
import com.fhhy.phoenix.contractdetail.dialog.HighQualityDesDialog
import kotlinx.android.synthetic.main.activity_high_quality_trader.*
import setViewClickListener

/**
 * Created by hecuncun on 2020/7/5
 */
class HighQualityTraderActivity :BaseMvpActivity<HighQualityTraderContract.View,HighQualityTraderContract.Presenter>(),HighQualityTraderContract.View,
    View.OnClickListener {
    private val followOrderAdapter: FollowOrderAdapter by lazy {
        FollowOrderAdapter()
    }
    override fun createPresenter(): HighQualityTraderContract.Presenter=HighQualityTraderPresenter()

    override fun getLayoutId(): Int = R.layout.activity_high_quality_trader

    override fun initView() {
        super.initView()
        setViewClickListener(this,iv_tip,btnBack)
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
            startActivity(Intent(this,TraderInfoActivity::class.java))

        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_tip->{
                HighQualityDesDialog.newInstance().show(supportFragmentManager,"tip")
            }
            R.id.btnBack->{
                finish()
            }
        }
    }
}