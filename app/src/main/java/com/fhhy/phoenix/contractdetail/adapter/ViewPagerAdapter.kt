package com.fhhy.phoenix.contractdetail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(
    private val fm: FragmentManager,
    private val behavior: Int,
    private val fragments: List<Fragment>
) :
    FragmentStatePagerAdapter(
        fm,
        behavior
    ) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int = fragments.size
}