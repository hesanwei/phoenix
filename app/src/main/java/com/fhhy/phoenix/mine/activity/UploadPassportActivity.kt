package com.fhhy.phoenix.mine.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.bean.AuthenticationBean
import com.fhhy.phoenix.constants.Constants
import com.fhhy.phoenix.dialog.UploadTipDialog
import com.fhhy.phoenix.dialog.bottomSingleChoiceDialog.DialogItem
import com.fhhy.phoenix.dialog.bottomSingleChoiceDialog.PersonalInfoSelectBean
import com.fhhy.phoenix.dialog.bottomSingleChoiceDialog.PersonalInfoSelectDialog
import com.fhhy.phoenix.mine.contract.UploadPassportContract
import com.fhhy.phoenix.mine.presenter.UploadPassportPresenter
import com.huantansheng.easyphotos.EasyPhotos
import com.huantansheng.easyphotos.models.album.entity.Photo
import getRequestMap
import kotlinx.android.synthetic.main.activity_upload_authentication.*
import kotlinx.android.synthetic.main.activity_upload_authentication.btnBack
import kotlinx.android.synthetic.main.activity_upload_authentication.tv_upload_tip
import kotlinx.android.synthetic.main.activity_upload_passport.*
import kotlinx.android.synthetic.main.activity_upload_passport.fl_upload
import kotlinx.android.synthetic.main.activity_upload_passport.iv_upload
import kotlinx.android.synthetic.main.activity_upload_passport.tv_submit
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import selectImageFromGallery
import setViewClickListener
import showToast
import takePhoto
import java.io.File
import java.util.*

/**
 * Created by heCunCun on 2020/7/13
 */
class UploadPassportActivity :
    BaseMvpActivity<UploadPassportContract.View, UploadPassportContract.Presenter>(),
    UploadPassportContract.View, View.OnClickListener {
    private var currentClick = 0 //点击那个图片 0 护照   1手持idCard
    private var currentPic: ImageView? = null//当前图片
    private var name = ""
    private var number = ""
    private var picFront = ""
    private var picHold = ""
    override fun getLayoutId(): Int = R.layout.activity_upload_passport

    override fun initView() {
        super.initView()
        name = intent.getStringExtra("name")!!
        number = intent.getStringExtra("number")!!
        setViewClickListener(this, btnBack, tv_upload_tip, fl_upload_passport, fl_upload, tv_submit)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_upload_tip -> {
                UploadTipDialog.newInstance().show(supportFragmentManager, "tip")
            }
            R.id.btnBack -> {
                finish()
            }
            R.id.fl_upload_passport -> {
                currentClick = 0
                clickAvatar()
            }
            R.id.fl_upload -> {
                currentClick = 1
                clickAvatar()
            }
            R.id.tv_submit -> {
                if (picFront.isNotEmpty() && picHold.isNotEmpty()) {
                    //全部选完图片
                    val requestMap = getRequestMap()
                    requestMap["idcard_name"] = name
                    requestMap["idcard_number"] = number
                    requestMap["type"] = "2"

                    val builder = MultipartBody.Builder()
                    builder.setType(MultipartBody.FORM)

                    val fileFront = File(picFront)
                    val requestFile1: RequestBody =
                        RequestBody.create(MediaType.parse("multipart/form-data"), fileFront)
                    builder.addFormDataPart("idcard_font", fileFront.name, requestFile1)

                    val fileHold = File(picHold)
                    val requestFile3: RequestBody =
                        RequestBody.create(MediaType.parse("multipart/form-data"), fileHold)
                    builder.addFormDataPart("idcard_hold", fileHold.name, requestFile3)

                    mPresenter?.requestAuthenticationIdCard(requestMap, builder.build().parts())
                } else {
                    showToast("请上传图片")
                }
            }
        }
    }

    /**
     * 点击修改上传
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
                            when (currentClick) {
                                0 -> {
                                    currentPic = iv_passport
                                    picFront=photo.path
                                }
                                1 -> {
                                    currentPic = iv_upload
                                    picHold=photo.path
                                }
                            }
                            Glide.with(this).load(photo.path).into(currentPic!!)
                            //mPresenter?.requestUploadAvatar(photo.path)
                        }
                    }
                }
            }
        }

    }

    override fun createPresenter(): UploadPassportContract.Presenter = UploadPassportPresenter()

    override fun requestAuthenticationIdCardSuccess(authenticationBean: AuthenticationBean?) {
        showToast("提交成功")
        finish()
    }
}