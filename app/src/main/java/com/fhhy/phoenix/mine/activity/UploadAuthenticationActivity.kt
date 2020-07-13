package com.fhhy.phoenix.mine.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.constants.Constants
import com.fhhy.phoenix.dialog.UploadTipDialog
import com.fhhy.phoenix.dialog.bottomSingleChoiceDialog.DialogItem
import com.fhhy.phoenix.dialog.bottomSingleChoiceDialog.PersonalInfoSelectBean
import com.fhhy.phoenix.dialog.bottomSingleChoiceDialog.PersonalInfoSelectDialog
import com.huantansheng.easyphotos.EasyPhotos
import com.huantansheng.easyphotos.models.album.entity.Photo
import kotlinx.android.synthetic.main.activity_upload_authentication.*
import kotlinx.android.synthetic.main.fragment_mine.*
import selectImageFromGallery
import setViewClickListener
import takePhoto
import java.util.ArrayList

/**
 * Created by heCunCun on 2020/7/13
 */
class UploadAuthenticationActivity:BaseActivity(), View.OnClickListener {
    private var currentClick=0 //点击那个图片 0 id前 2id 后  3手持idCard
    private var currentPic:ImageView?=null//当前图片

    override fun getLayoutId(): Int = R.layout.activity_upload_authentication

    override fun initView() {
             setViewClickListener(this,btnBack,tv_upload_tip,fl_upload_front,fl_upload_back,fl_upload)
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
                                0->{ currentPic=iv_front}
                                1->{currentPic=iv_back}
                                2->{currentPic=iv_upload}
                            }
                            Glide.with(this).load(photo.path).into(currentPic!!)
                            //mPresenter?.requestUploadAvatar(photo.path)
                        }
                    }
                }
            }
        }

    }
}