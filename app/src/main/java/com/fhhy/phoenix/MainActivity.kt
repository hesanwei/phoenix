package com.fhhy.phoenix

import androidx.core.view.get
import androidx.fragment.app.FragmentTransaction
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.community.fragment.CommunityFragment
import com.fhhy.phoenix.contract.fragment.ContractFragment
import com.fhhy.phoenix.exchange.fragment.ExchangeFragment
import com.fhhy.phoenix.home.fragment.HomeFragment
import com.fhhy.phoenix.mine.fragment.MineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var mIndex: Int = FRAGMENT_HOME
    private var mHomeFragment: HomeFragment? = null
    private var mContractFragment: ContractFragment? = null
    private var mExchangeFragment: ExchangeFragment? = null
    private var mCommunityFragment: CommunityFragment? = null
    private var mMineFragment: MineFragment? = null

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        StatusBarUtil.setLightMode(this)
        bottomNavigationView.run {
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
            itemIconTintList = null
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }
        showFragment(mIndex)
    }

    /**
     * NavigationItemSelect监听
     */
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    showFragment(FRAGMENT_HOME)
                    true
                }
                R.id.nav_contract -> {
                    showFragment(FRAGMENT_CONTRACT)
                    true
                }
                R.id.nav_exchange -> {
                    showFragment(FRAGMENT_EXCHANGE)
                    true
                }
                R.id.nav_community -> {
                    showFragment(FRAGMENT_COMMUNITY)
                    true
                }
                R.id.nav_mine -> {
                    showFragment(FRAGMENT_MINE)
                    true
                }
                else -> {
                    false
                }

            }
        }

    /**
     * 展示Fragment
     * @param index
     */
    private fun showFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        mIndex = index
        when (index) {
            FRAGMENT_HOME // 首页
            -> {
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance()
                    transaction.add(R.id.container, mHomeFragment!!, "home")
                } else {
                    transaction.show(mHomeFragment!!)
                }
            }
            FRAGMENT_CONTRACT  // 合约
            -> {
                if (mContractFragment == null) {
                    mContractFragment = ContractFragment.newInstance()
                    transaction.add(R.id.container, mContractFragment!!, "contract")
                } else {
                    transaction.show(mContractFragment!!)
                }
            }
            FRAGMENT_EXCHANGE //兑币
            -> {
                if (mExchangeFragment == null) {
                    mExchangeFragment = ExchangeFragment.newInstance()
                    transaction.add(R.id.container, mExchangeFragment!!, "exchange")
                } else {
                    transaction.show(mExchangeFragment!!)
                }
            }
            FRAGMENT_COMMUNITY // 社区
            -> {
                if (mCommunityFragment == null) {
                    mCommunityFragment = CommunityFragment.newInstance()
                    transaction.add(R.id.container, mCommunityFragment!!, "community")
                } else {
                    transaction.show(mCommunityFragment!!)
                }
            }
            FRAGMENT_MINE // 我的
            -> {
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.newInstance()
                    transaction.add(R.id.container, mMineFragment!!, "mine")
                } else {
                    transaction.show(mMineFragment!!)
                }
            }
        }
        transaction.commit()
    }

    /**
     * 隐藏所有的Fragment
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mContractFragment?.let { transaction.hide(it) }
        mExchangeFragment?.let { transaction.hide(it) }
        mCommunityFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    companion object {
        private const val FRAGMENT_HOME = 0x01
        private const val FRAGMENT_CONTRACT = 0x02
        private const val FRAGMENT_EXCHANGE = 0x03
        private const val FRAGMENT_COMMUNITY = 0x04
        private const val FRAGMENT_MINE = 0x05
    }

}