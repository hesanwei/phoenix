package com.fhhy.phoenix.mine.activity

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.bean.UploadBean
import com.fhhy.phoenix.bean.UserInfoBean
import com.fhhy.phoenix.constants.Constants
import com.fhhy.phoenix.constants.SPKeyConstants
import com.fhhy.phoenix.dialog.bottomSingleChoiceDialog.DialogItem
import com.fhhy.phoenix.dialog.bottomSingleChoiceDialog.PersonalInfoSelectBean
import com.fhhy.phoenix.dialog.bottomSingleChoiceDialog.PersonalInfoSelectDialog
import com.fhhy.phoenix.event.UpdatePersonalInfoSuccessEvent
import com.fhhy.phoenix.mine.contract.PersonalInfoContract
import com.fhhy.phoenix.mine.presenter.PersonalInfoPresenter
import com.fhhy.phoenix.toast.ToastUtil
import com.huantansheng.easyphotos.EasyPhotos
import com.huantansheng.easyphotos.models.album.entity.Photo
import copyText
import kotlinx.android.synthetic.main.activity_personal_info.*
import kotlinx.android.synthetic.main.fragment_mine.civAvatar
import org.greenrobot.eventbus.EventBus
import selectImageFromGallery
import setViewClickListener
import showToast
import takePhoto
import java.util.ArrayList

// Created by admin on 2020/7/11.
class PersonalInfoActivity :
    BaseMvpActivity<PersonalInfoContract.View, PersonalInfoContract.Presenter>(),
    PersonalInfoContract.View, View.OnClickListener {
    override fun createPresenter(): PersonalInfoContract.Presenter = PersonalInfoPresenter()

    override fun getLayoutId(): Int = R.layout.activity_personal_info

    override fun initView() {
        super.initView()
        mPresenter?.requestUserInfo()
        setViewClickListener(
            this,
            llAvatar,
            clNickName,
            clUID,
            clGender,
            clLocation,
            tvSave,
            btnBack
        )
    }

    override fun requestUserInfoSuccess(userInfoBean: UserInfoBean?) {
        // TODO: 2020/7/11 字段不全
        userInfoBean?.apply {
            Glide.with(this@PersonalInfoActivity)
                .load(avatar)
                .error(R.mipmap.icon_default_avatar)
                .placeholder(R.mipmap.icon_default_avatar)
                .into(civAvatar)
            tvNickName.text = checkNull(nick_name)
            tvUID.text = id
            tvGender.text = checkNull(sex)
            tvMobile.text = mobile
        }
    }

    override fun requestSetPersonalInfoSuccess(
        message: String?, nick_name: String?,
        sex: String?,
        profile: String?,
        country: String?
    ) {
        if (TextUtils.isEmpty(message)) {
            ToastUtil.showOperateTip()
        } else {
            showToast(message)
        }
        if (!TextUtils.isEmpty(nick_name)) {
            EventBus.getDefault().post(UpdatePersonalInfoSuccessEvent())
            tvNickName.text = nick_name
        }

        if (!TextUtils.isEmpty(country)) {
            tvLocation.text = country
        }
    }

    override fun requestUploadAvatarSuccess(message: String?, uploadBean: UploadBean?) {
        showToast(message)
        EventBus.getDefault().post(UpdatePersonalInfoSuccessEvent())
    }

    private fun checkNull(string: String?): String {
        return if (TextUtils.isEmpty(string)) resources.getString(R.string.no_setting) else string!!
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.llAvatar -> {//点击修改头像
                clickAvatar()
            }

            R.id.clNickName -> {
                startActivityForResult(
                    Intent(this, ModifyNickNameActivity::class.java),
                    Constants.MODIFY_NAME_REQUEST_CODE
                )
            }

            R.id.clUID -> {
                tvUID.text?.toString()?.copyText(this)
            }

            R.id.clGender -> {
                clickGender()//点击性别
            }

            R.id.clLocation -> {

            }

            R.id.tvSave -> {
                val profile = etPersonalIntroduction?.text?.toString()
                mPresenter?.requestSetPersonalInfo(profile = profile)
            }

            R.id.btnBack -> {
                finish()
            }
        }
    }

    /**
     * 点击修改头像
     */
    private fun clickAvatar() {
        val list = mutableListOf(
            PersonalInfoSelectBean(
                DialogItem.ITEM_TAKE_PHOTO,
                resources.getString(R.string.take_photo)
            ),
            PersonalInfoSelectBean(
                DialogItem.ITEM_FROM_PHOTO_ALBUM,
                resources.getString(R.string.select_from_photo_album)
            )
        )
        PersonalInfoSelectDialog(list, object : PersonalInfoSelectDialog.OnItemClickListener {
            override fun onItemClick(bean: PersonalInfoSelectBean?) {
                when (bean?.dialogItem) {
                    DialogItem.ITEM_TAKE_PHOTO -> {
                        takePhoto()
                    }

                    DialogItem.ITEM_FROM_PHOTO_ALBUM -> {
                        selectImageFromGallery()
                    }
                    else -> {

                    }
                }
            }
        }).show(supportFragmentManager)
    }

    /**
     * 点击性别
     */
    private fun clickGender() {
        val list = mutableListOf(
            PersonalInfoSelectBean(
                DialogItem.ITEM_GENDER_MALE,
                resources.getString(R.string.male)
            ),
            PersonalInfoSelectBean(
                DialogItem.ITEM_GENDER_FEMALE,
                resources.getString(R.string.female)
            )
        )
        PersonalInfoSelectDialog(list, object : PersonalInfoSelectDialog.OnItemClickListener {
            override fun onItemClick(bean: PersonalInfoSelectBean?) {
                tvGender?.text = bean?.name
                val sex = if (DialogItem.ITEM_GENDER_MALE == bean?.dialogItem) "1" else "2"
                mPresenter?.requestSetPersonalInfo(sex = sex)
            }
        }).show(supportFragmentManager)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Constants.SELECT_IMAGE_REQUEST_CODE -> {
                    //返回对象集合：如果你需要了解图片的宽、高、大小、用户是否选中原图选项等信息，可以用这个
                    if (data != null) {
                        val resultPhotos: ArrayList<Photo>? =
                            data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS)
                        if (resultPhotos != null && resultPhotos.isNotEmpty()) {
                            val photo = resultPhotos[0]
                            Glide.with(this).load(photo.path).into(civAvatar)
                            mPresenter?.requestUploadAvatar(photo.path)
                        }
                    }
                }
                Constants.MODIFY_NAME_REQUEST_CODE -> {
                    val extras = data?.extras
                    if (extras != null) {
                        val name = extras[SPKeyConstants.SP_KEY_NICKNAME]
                        mPresenter?.requestSetPersonalInfo(nick_name = name?.toString())
                    }
                }
            }
        }
    }
}