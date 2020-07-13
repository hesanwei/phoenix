package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import kotlinx.android.synthetic.main.activity_authentication.*
import setViewClickListener

/**
 * Created by hecuncun on 2020/7/12
 */
class AuthenticationActivity :BaseActivity(), View.OnClickListener {
    private var checked=0 //0身份证  1护照
    override fun getLayoutId(): Int= R.layout.activity_authentication

    override fun initView() {
         setViewClickListener(this,ll_id_card,ll_passport,tv_next,btnBack)
    }

    override fun onClick(v: View?) {
       when(v?.id){
           R.id.ll_id_card->{
              if (checked==1){
                  checked=0
                  ll_id_card.setBackgroundResource(R.drawable.white_rg_checked_dp5)
                  ll_passport.setBackgroundResource(R.drawable.white_rg_uncheck_dp5)
                  iv_rb_id.setImageResource(R.mipmap.icon_rg_checked)
                  iv_rb_passport.setImageResource(R.mipmap.icon_rg_uncheck)
              }
           }
           R.id.ll_passport->{
               if (checked==0){
                   checked=1
                   ll_passport.setBackgroundResource(R.drawable.white_rg_checked_dp5)
                   ll_id_card.setBackgroundResource(R.drawable.white_rg_uncheck_dp5)
                   iv_rb_passport.setImageResource(R.mipmap.icon_rg_checked)
                   iv_rb_id.setImageResource(R.mipmap.icon_rg_uncheck)
               }
           }
           R.id.tv_next->{
               if (checked==0){
                   startActivity(Intent(this,UploadAuthenticationActivity::class.java))
               }else{
                   startActivity(Intent(this,UploadPassportActivity::class.java))
               }

           }
           R.id.btnBack->{
               finish()
           }
       }
    }
}