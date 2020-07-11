package com.fhhy.phoenix.community.activity

import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.community.adapter.FollowOrderAdapter
import com.fhhy.phoenix.community.contract.SearchTraderContract
import com.fhhy.phoenix.community.presenter.SearchTraderPresenter
import kotlinx.android.synthetic.main.activity_search_trader.*
import kotlinx.android.synthetic.main.activity_search_trader.recyclerView
import setViewClickListener
import showToast

/**
 * Created by hecuncun on 2020/7/5
 */
class SearchTraderActivity:BaseMvpActivity<SearchTraderContract.View,SearchTraderContract.Presenter> (),SearchTraderContract.View,
    View.OnClickListener {
    private val followOrderAdapter: FollowOrderAdapter by lazy {
        FollowOrderAdapter()
    }
    override fun createPresenter(): SearchTraderContract.Presenter=SearchTraderPresenter()

    override fun getLayoutId(): Int= R.layout.activity_search_trader

    override fun initView() {
        super.initView()
        initData()
        setViewClickListener(this,tv_cancel,iv_et_delete)
        initRecyclerView()
        et_search.setOnEditorActionListener { v, actionId, event ->
        when(actionId){
            EditorInfo.IME_ACTION_SEARCH->{
                showToast("点击搜索")

            }
        }
            true
        }
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

    private fun initData() {

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_cancel->{
                finish()
            }
            R.id.iv_et_delete->{
                et_search.setText("")
            }
        }
    }

}