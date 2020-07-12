package com.fhhy.phoenix.mine.fragment

import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.bean.UserInfoBean
import com.fhhy.phoenix.dialog.CoinSelectDialog
import com.fhhy.phoenix.login.LoginActivity
import com.fhhy.phoenix.event.LoginSuccessEvent
import com.fhhy.phoenix.event.UpdatePersonalInfoSuccessEvent
import com.fhhy.phoenix.mine.activity.*
import com.fhhy.phoenix.mine.activity.FundsAccountDetailActivity.Companion.FUNDS_NAME
import com.fhhy.phoenix.mine.contract.MineContract
import com.fhhy.phoenix.mine.presenter.MinePresenter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_mine.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import setViewClickListener

// Created by admin on 2020/6/7.
class MineFragment : BaseMvpFragment<MineContract.View, MineContract.Presenter>(),
    MineContract.View,
    View.OnClickListener {

    private var isFundsVisible = true

    override fun useEventBus(): Boolean = true

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
            tvLogin,
            llLogin,
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
        initLoginStatus()
    }

    private fun initLoginStatus() {
        if (isLogin()) {
            llLogin.visibility = View.VISIBLE
            tvLogin.visibility = View.GONE
            llTotalAssets.visibility = View.VISIBLE
            tvTotalAssetsNoLogin.visibility = View.GONE
            tvFunds2U.visibility = View.VISIBLE
            tvFull2U.visibility = View.VISIBLE
            mPresenter?.requestUserInfo()
        } else {
            llLogin.visibility = View.GONE
            tvLogin.visibility = View.VISIBLE
            llTotalAssets.visibility = View.INVISIBLE
            tvTotalAssetsNoLogin.visibility = View.VISIBLE
            tvFunds2U.visibility = View.INVISIBLE
            tvFull2U.visibility = View.INVISIBLE
            tvTotalFunds.text = resources.getString(R.string.no_login_text)
            tvTotalFull.text = resources.getString(R.string.no_login_text)
        }
    }

    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.tvLogin -> {
                startActivity(Intent(context, LoginActivity::class.java))
            }

            R.id.llLogin -> {
                startActivity(Intent(requireContext(), PersonalInfoActivity::class.java))
            }

            R.id.ivEyes -> {//资产可见不可见
                if (isLogin()) {
                    isFundsVisible = !isFundsVisible
                    setFundsVisible()
                }
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
                startActivity(Intent(context, MyBonusActivity::class.java))
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLoginSuccess(event: LoginSuccessEvent) {
        lazyLoad()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLoginSuccess(event: UpdatePersonalInfoSuccessEvent) {
        lazyLoad()
    }

    override fun requestUserInfoSuccess(userInfoBean: UserInfoBean?) {
        // TODO: 2020/7/11 缺少字段
        userInfoBean?.apply {
            Glide.with(requireContext()).load(avatar).error(R.mipmap.icon_default_avatar)
                .into(civAvatar)
            tvUserName?.text = nick_name
            tvTotalAssets?.text = money
        }
    }
}