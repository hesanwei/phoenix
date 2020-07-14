package com.fhhy.phoenix.mine.activity

import android.app.Activity
import android.content.Intent
import android.util.Log
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
import com.fhhy.phoenix.mine.contract.UploadAuthenticationContract
import com.fhhy.phoenix.mine.presenter.UploadAuthenticationPresenter
import com.huantansheng.easyphotos.EasyPhotos
import com.huantansheng.easyphotos.models.album.entity.Photo
import getRequestMap
import kotlinx.android.synthetic.main.activity_upload_authentication.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import selectImageFromGallery
import setViewClickListener
import showToast
import takePhoto
import java.io.File
import java.util.ArrayList

/**
 * Created by heCunCun on 2020/7/13
 */
class UploadAuthenticationActivity: BaseMvpActivity<UploadAuthenticationContract.View,UploadAuthenticationContract.Presenter>(), UploadAuthenticationContract.View,View.OnClickListener {
    private var currentClick=0 //点击那个图片 0 id前 2id 后  3手持idCard
    private var currentPic:ImageView?=null//当前图片
    private var picFront=""
    private var picBack=""
    private var picHold=""
    private var name =""
    private var number =""
    override fun createPresenter(): UploadAuthenticationContract.Presenter=UploadAuthenticationPresenter()
    override fun getLayoutId(): Int = R.layout.activity_upload_authentication

    override fun initView() {
        name = intent.getStringExtra("name")!!
        number= intent.getStringExtra("number")!!
        setViewClickListener(this,btnBack,tv_upload_tip,fl_upload_front,fl_upload_back,fl_upload,tv_submit)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_upload_tip->{
                UploadTipDialog.newInstance().show(supportFragmentManager,"tip")
            }
            R.id.btnBack->{
                finish()
            }
            R.id.fl_upload_front->{
                currentClick=0
                clickAvatar()
            }
            R.id.fl_upload_back->{
                currentClick=1
                clickAvatar()
            }
            R.id.fl_upload->{
                currentClick=2
                clickAvatar()
            }
            R.id.tv_submit->{
                if (picFront.isNotEmpty() && picBack.isNotEmpty() && picHold.isNotEmpty()){
                    //全部选完图片
                    val requestMap = getRequestMap()
                    requestMap["idcard_name"] = name
                    requestMap["idcard_number"] = number
                    requestMap["type"] = "1"

                    val builder = MultipartBody.Builder()
                    builder.setType(MultipartBody.FORM)

                    val fileFront = File(picFront)
                    val requestFile1: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), fileFront)
                    builder.addFormDataPart("idcard_font", fileFront.name, requestFile1)

                    val fileBack = File(picBack)
                    val requestFile2: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), fileBack)
                    builder.addFormDataPart("idcard_back", fileBack.name, requestFile2)

                    val fileHold= File(picHold)
                    val requestFile3: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), fileHold)
                    builder.addFormDataPart("idcard_hold", fileHold.name, requestFile3)

                    mPresenter?.requestAuthenticationIdCard(requestMap,builder.build().parts())
                }else{
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
        if (resultCode== Activity.RESULT_OK){
            when(requestCode){
                Constants.SELECT_IMAGE_REQUEST_CODE -> {
                    //返回对象集合：如果你需要了解图片的宽、高、大小、用户是否选中原图选项等信息，可以用这个
                    if (data != null) {
                        val resultPhotos: ArrayList<Photo>? =
                            data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS)
                        if (resultPhotos != null && resultPhotos.isNotEmpty()) {
                            val photo = resultPhotos[0]
                            when(currentClick){
                                0->{
                                    currentPic=iv_front
                                    picFront=photo.path
                                }
                                1->{
                                    currentPic=iv_back
                                    picBack=photo.path
                                }
                                2->{
                                    currentPic=iv_upload
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

    override fun requestAuthenticationIdCardSuccess(authenticationBean: AuthenticationBean?) {
           Log.e("hcc","url1=${authenticationBean?.urlList?.get(0)}")
        showToast("提交成功")
    }

}