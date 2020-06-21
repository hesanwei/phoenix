package com.fhhy.phoenix.mine.fragment

import android.content.Intent
import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.dialog.CoinSelectDialog
import com.fhhy.phoenix.login.LoginActivity
import com.fhhy.phoenix.mine.activity.AccountActivity
import com.fhhy.phoenix.mine.activity.FundsAccountDetailActivity.Companion.FUNDS_NAME
import com.fhhy.phoenix.mine.activity.RechargeActivity
import com.fhhy.phoenix.mine.activity.WithdrawActivity
import com.fhhy.phoenix.mine.contract.MineContract
import com.fhhy.phoenix.mine.presenter.MinePresenter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_mine.*
import setViewClickListener

// Created by admin on 2020/6/7.
class MineFragment : BaseMvpFragment<MineContract.View, MineContract.Presenter>(),
    View.OnClickListener {

    private var isFundsVisible = true

    override fun createPresenter(): MineContract.Presenter = MinePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity, smartRefreshLayout)
        StatusBarUtil.setLightMode(activity)
        initViewClick()
    }

    private fun initViewClick() {
        setViewClickListener(
            this,
            clUserInfo,
            ivEyes,
            llFundsAccount,
            llFullAccount,
            tvRecharge,
            tvWithdraw,
            tvFundsTransfer,
            ivInviteFriends,
            mivMyBonus,
            mivDocumentaryManagement,
            mivRecommendDocumentary,
            mivContactCustomerService,
            mivHelpCenter,
            mivMyInviter,
            mivAuthentication,
            mivSecurityCenter,
            mivSettings,
            mivAboutUs
        )
    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.clUserInfo -> {
                startActivity(Intent(context, LoginActivity::class.java))
            }

            R.id.ivEyes -> {//资产可见不可见
                isFundsVisible = !isFundsVisible
                setFundsVisible()
            }

            R.id.llFundsAccount -> {//资金账户
                val intent = Intent(context, AccountActivity::class.java)
                intent.putExtra(AccountActivity.ACCOUNT_TYPE, AccountActivity.ACCOUNT_TYPE_FUNDS)
                startActivity(intent)
            }
            R.id.llFullAccount -> {//全仓账户
                val intent = Intent(context, AccountActivity::class.java)
                intent.putExtra(AccountActivity.ACCOUNT_TYPE, AccountActivity.ACCOUNT_TYPE_FULL)
                startActivity(intent)
            }
            R.id.tvRecharge -> {//充值
                CoinSelectDialog(mutableListOf("BTC", "ETH", "USDT"),
                    object : CoinSelectDialog.OnItemSelectListener {
                        override fun onItemSelect(coin: String) {
                            val intent = Intent(context, RechargeActivity::class.java)
                            intent.putExtra(FUNDS_NAME, coin)
                            startActivity(intent)
                        }
                    }).show(activity!!.supportFragmentManager)
            }
            R.id.tvWithdraw -> {//提币
                CoinSelectDialog(mutableListOf("BTC", "ETH", "USDT"),
                    object : CoinSelectDialog.OnItemSelectListener {
                        override fun onItemSelect(coin: String) {
                            val intent = Intent(context, WithdrawActivity::class.java)
                            intent.putExtra(FUNDS_NAME, coin)
                            startActivity(intent)
                        }
                    }).show(activity!!.supportFragmentManager)
            }

            R.id.tvFundsTransfer -> {//资金划转

            }
            R.id.ivInviteFriends -> {//邀请好友

            }
            R.id.mivMyBonus -> {//我的赠金

            }
            R.id.mivDocumentaryManagement -> {//跟单管理

            }
            R.id.mivRecommendDocumentary -> {//推荐跟单

            }
            R.id.mivContactCustomerService -> {//联系客服

            }
            R.id.mivHelpCenter -> {//帮助中心

            }
            R.id.mivMyInviter -> {//我的邀请人

            }
            R.id.mivAuthentication -> {//身份认证

            }
            R.id.mivSecurityCenter -> {//安全中心

            }
            R.id.mivSettings -> {//设置

            }
            R.id.mivAboutUs -> {//关于我们

            }
        }
    }

    /**
     * 点击眼睛，可见不可见
     */
    private fun setFundsVisible() {
        if (isFundsVisible) {
            ivEyes.setImageResource(R.mipmap.icon_eyes_open)
            tvAssetsCurrency.visibility = View.VISIBLE
            tvConvertedCurrency.visibility = View.VISIBLE
            tvFunds2U.visibility = View.VISIBLE
            tvFull2U.visibility = View.VISIBLE
            tvTotalAssets.text = "0.002"
            tvTotalFunds.text = "0.0002"
            tvTotalFull.text = "0.0002"

        } else {
            ivEyes.setImageResource(R.mipmap.icon_eyes_close)
            tvAssetsCurrency.visibility = View.INVISIBLE
            tvConvertedCurrency.visibility = View.INVISIBLE
            tvFunds2U.visibility = View.INVISIBLE
            tvFull2U.visibility = View.INVISIBLE
            tvTotalAssets.text = "*****"
            tvTotalFunds.text = "*****"
            tvTotalFull.text = "*****"
        }
    }
}