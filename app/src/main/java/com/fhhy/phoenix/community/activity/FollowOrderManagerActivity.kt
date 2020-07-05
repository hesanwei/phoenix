package com.fhhy.phoenix.community.activity

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.community.adapter.TraderInfoAdapter
import com.fhhy.phoenix.community.contract.FollowOrderManagerContract
import com.fhhy.phoenix.community.presenter.FollowOrderManagerPresenter
import com.fhhy.phoenix.contractdetail.dialog.RealFollowOrderProfitDesDialog
import kotlinx.android.synthetic.main.activity_follow_order_manager.*
import kotlinx.android.synthetic.main.activity_follow_order_manager.radioGroup
import kotlinx.android.synthetic.main.activity_follow_order_manager.recyclerView
import setViewClickListener
import underline

/**
 * Created by hecuncun on 2020/7/5
 */
class FollowOrderManagerActivity:BaseMvpActivity<FollowOrderManagerContract.View,FollowOrderManagerContract.Presenter>(),FollowOrderManagerContract.View,
    View.OnClickListener {
    private val traderInfoAdapter:TraderInfoAdapter by lazy {
        TraderInfoAdapter()
    }
    override fun createPresenter(): FollowOrderManagerContract.Presenter=FollowOrderManagerPresenter()

    override fun getLayoutId(): Int = R.layout.activity_follow_order_manager

    override fun initView() {
        super.initView()
        setViewClickListener(this,btnBack,profit_total)
        radioGroup.check(R.id.rb_real)
        profit_total.underline()
        initRecyclerView()
        initRadioGroup()
    }

    private fun initRadioGroup() {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rb_real->{
                    total_money.text="实盘累计跟单金额(USDT)"
                    profit_total.text="实盘跟单总收益(USDT)"
                    profit_total.underline()
                }
                R.id.rb_simulate->{
                    total_money.text="模拟盘累计跟单金额(VST)"
                    profit_total.text="模拟盘跟单总收益(VST)"
                }
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FollowOrderManagerActivity)
            traderInfoAdapter.data = mutableListOf("", "", "", "", "")
            adapter = traderInfoAdapter
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnBack->{finish()}
            R.id.profit_total->{
                //弹窗
                if (radioGroup.checkedRadioButtonId== R.id.rb_real){
                    RealFollowOrderProfitDesDialog.newInstance().show(supportFragmentManager,"real")
                }

            }
        }
    }
}