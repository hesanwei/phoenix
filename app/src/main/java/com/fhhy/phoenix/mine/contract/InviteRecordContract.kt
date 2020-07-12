package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.InviteRecordBean
import io.reactivex.Observable

// Created by admin on 2020/7/12.
interface InviteRecordContract {
    interface View : IView {
        fun requestInviteRecordSuccess(inviteRecordBean: InviteRecordBean?)
    }

    interface Model : IModel {
        fun requestInviteRecord(page: Int): Observable<HttpResult<InviteRecordBean?>>
    }

    interface Presenter : IPresenter<View> {
        fun requestInviteRecord(page: Int)
    }
}