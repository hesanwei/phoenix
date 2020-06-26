package com.fhhy.phoenix.contractdetail.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.databinding.FragmentDelegateListBinding
import com.fhhy.phoenix.test.DelegateBean
import noDoubleClick

class DelegateListFragment : BaseVBFragment<FragmentDelegateListBinding>() {

    val mAdapter: DelegateAdapter by lazy {
        DelegateAdapter()
    }
    var addFooter = false
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDelegateListBinding =
        FragmentDelegateListBinding.inflate(inflater, container, false)

    override fun setupViews() {
        mBinding.rvDelegateList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }

        mAdapter.addHeaderView(layoutInflater.inflate(R.layout.header_delegate_list, mBinding.rvDelegateList.parent as ViewGroup, false))


    }

    override fun setupListeners() {

    }

    override fun setupObservers() {
        val testData = arrayListOf<DelegateBean>()
        for (i in 1..20) {
            testData.add(DelegateBean("0.520175", "0.520175", "9424.7", "9424.5"))
        }
        mAdapter.setList(testData)

        if (!addFooter) {
            addFooter = true
            val footer = layoutInflater.inflate(R.layout.footer_delegate_more, mBinding.rvDelegateList.parent as ViewGroup, false)
            footer.findViewById<View>(R.id.btn_more).noDoubleClick {
                Toast.makeText(requireContext(), "Look More", Toast.LENGTH_SHORT).show()
            }
            mAdapter.addFooterView(footer)
        }

    }

    companion object {
        fun create(): DelegateListFragment {
            return DelegateListFragment()
        }
    }
}