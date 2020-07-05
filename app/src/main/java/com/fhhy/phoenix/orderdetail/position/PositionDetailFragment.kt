package com.fhhy.phoenix.orderdetail.position

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.databinding.FragmentOrderPositionDetailBinding

class PositionDetailFragment : BaseVBFragment<FragmentOrderPositionDetailBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrderPositionDetailBinding =
        FragmentOrderPositionDetailBinding.inflate(
            inflater,
            container, false
        )

    override fun setupViews() {
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }

    companion object {
        const val TAG = "PositionDetailFragment"
        fun create(): PositionDetailFragment {
            return PositionDetailFragment()
        }
    }
}