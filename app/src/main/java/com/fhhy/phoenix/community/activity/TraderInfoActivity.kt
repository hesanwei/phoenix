package com.fhhy.phoenix.community.activity

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.community.adapter.TagAdapter
import com.fhhy.phoenix.community.contract.TraderInfoContract
import com.fhhy.phoenix.community.fragment.FollowerFragment
import com.fhhy.phoenix.community.fragment.HistoricalPositionFragment
import com.fhhy.phoenix.community.presenter.TraderInfoPresenter
import com.fhhy.phoenix.contractdetail.dialog.ShareLinkDialog
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_order.radioGroup
import kotlinx.android.synthetic.main.activity_trader_info.*
import kotlinx.android.synthetic.main.activity_trader_info.recyclerView
import setViewClickListener
import showToast
import kotlin.math.abs

/**
 * Created by hecuncun on 2020/7/5
 */
class TraderInfoActivity:BaseMvpActivity<TraderInfoContract.View,TraderInfoContract.Presenter>(),TraderInfoContract.View,
    View.OnClickListener {

    private val tagAdapter: TagAdapter by lazy {
        TagAdapter()
    }

    private val historicalPositionFragment: HistoricalPositionFragment by lazy {
        HistoricalPositionFragment.newInstance()
    }

    private val followerFragment:FollowerFragment by lazy {
        FollowerFragment.newInstance()
    }
    override fun createPresenter(): TraderInfoContract.Presenter =TraderInfoPresenter()

    override fun getLayoutId(): Int= R.layout.activity_trader_info

    override fun initView() {
        super.initView()
        setViewClickListener(this,iv_back,iv_back_2,iv_back_3,iv_share,iv_share_2)
        initRadioGroup()
        initRecyclerView()
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
                verticalOffset==0 -> {
                    //完全展开
                    rl_title.visibility=View.GONE
                    ll_btn_container.visibility=View.VISIBLE

                }
                abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                    //折叠状态
                    rl_title.visibility=View.VISIBLE
                    ll_btn_container.visibility=View.GONE

                }
                else -> {
                    //中间状态
                    rl_title.visibility=View.GONE
                    ll_btn_container.visibility=View.VISIBLE
                }
            }
        })
        //显示空数据
        showNoData(false)
    }
    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = tagAdapter
            tagAdapter.data = mutableListOf("", "", "","","")
        }
        tagAdapter.setOnItemClickListener { adapter, view, position ->
            // startActivity(Intent(requireContext(), TraderInfoActivity::class.java))
        }
    }
    /**
     * 空白布局显示开关
     */
    private fun showNoData(show:Boolean) {
        if (show){
            fl_no_data.visibility=View.VISIBLE
        }else{
            fl_no_data.visibility=View.GONE
        }

    }

    private fun initRadioGroup() {
        replaceFragment(historicalPositionFragment)
        radioGroup.check(R.id.rb_history)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_history -> replaceFragment(historicalPositionFragment)
                R.id.rb_follower -> replaceFragment(followerFragment)
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.flContainer, fragment)
        beginTransaction.commit()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back,R.id.iv_back_2,R.id.iv_back_3->{
                finish()
            }
            R.id.iv_share, R.id.iv_share_2->{
                ShareLinkDialog.newInstance().setOnConfirmListener(object :ShareLinkDialog.OnConfirmListener{
                    override fun onConfirm() {
                        showToast("分享链接")
                    }

                }).show(supportFragmentManager,"share")
            }
        }
    }
}