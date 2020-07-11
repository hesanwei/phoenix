package com.fhhy.phoenix.mine.activity

import android.text.TextUtils
import com.bumptech.glide.Glide
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.bean.UserInfoBean
import com.fhhy.phoenix.mine.contract.PersonalInfoContract
import com.fhhy.phoenix.mine.presenter.PersonalInfoPresenter
import kotlinx.android.synthetic.main.activity_personal_info.*
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.fragment_mine.civAvatar

// Created by admin on 2020/7/11.
class PersonalInfoActivity :
    BaseMvpActivity<PersonalInfoContract.View, PersonalInfoContract.Presenter>(),
    PersonalInfoContract.View {
    override fun createPresenter(): PersonalInfoContract.Presenter = PersonalInfoPresenter()

    override fun getLayoutId(): Int = R.layout.activity_personal_info

    override fun initView() {
        super.initView()
        mPresenter?.requestUserInfo()
    }

    override fun requestUserInfoSuccess(userInfoBean: UserInfoBean?) {
        // TODO: 2020/7/11 字段不全
        userInfoBean?.apply {
            Glide.with(this@PersonalInfoActivity)
                .load(avatar)
                .error(R.mipmap.icon_default_avatar)
                .into(civAvatar)
            tvNickName.text = if (TextUtils.isEmpty(nick_name)) resources.getString(R.string.no_setting) else nick_name

        }
    }
}