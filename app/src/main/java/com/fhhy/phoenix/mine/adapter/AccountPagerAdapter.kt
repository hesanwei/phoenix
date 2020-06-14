package com.fhhy.phoenix.mine.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fhhy.phoenix.base.BaseFragment

// Created by admin on 2020/6/14.
class AccountPagerAdapter(
    fragmentManager: FragmentManager,
    private val titleList: List<String>,
    private val fragmentList: List<Fragment>
) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment = fragmentList[position]


    override fun getCount(): Int = titleList.size

    override fun getPageTitle(position: Int): CharSequence? = titleList[position]
}