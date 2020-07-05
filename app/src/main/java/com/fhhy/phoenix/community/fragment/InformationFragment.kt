package com.fhhy.phoenix.community.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.community.adapter.InformationAdapter
import com.fhhy.phoenix.community.contract.InformationContract
import com.fhhy.phoenix.community.presenter.InformationPresenter
import kotlinx.android.synthetic.main.fragment_order.*

/**
 * Created by hecuncun on 2020/7/4
 */
class InformationFragment :
    BaseMvpFragment<InformationContract.View, InformationContract.Presenter>() {
    private val infoAdapter: InformationAdapter by lazy {
        InformationAdapter()
    }

    override fun createPresenter(): InformationContract.Presenter = InformationPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_information
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
            adapter = infoAdapter
            infoAdapter.data = mutableListOf("", "", "","","","","","")
        }
        infoAdapter.setOnItemClickListener { adapter, view, position ->

        }
    }

    companion object {
        fun newInstance(): InformationFragment {
            return InformationFragment()
        }
    }
}