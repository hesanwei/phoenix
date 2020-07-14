import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseApplication
import com.fhhy.phoenix.base.BaseBean
import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.constants.Constants
import com.fhhy.phoenix.http.ExceptionWrapper
import com.fhhy.phoenix.http.ExceptionWrapper.Companion.ERROR_CODE_UNKNOWN
import com.fhhy.phoenix.http.ExceptionWrapper.Companion.ERROR_MSG_UNKNOWN
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import okhttp3.RequestBody

/**
 * Created by admin on 2020/1/13.
 */

fun <T : BaseBean> Observable<T>.request(
    model: IModel?,
    view: IView?,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit
) {
    this.compose(SchedulerUtils.ioToMain())
        .retryWhen(RetryWithDelay())
        .subscribe(object : Observer<T> {
            override fun onComplete() {
                if (isShowLoading) {
                    view?.hideLoading()
                }
            }

            override fun onSubscribe(d: Disposable) {
                if (isShowLoading) view?.showLoading()
                model?.addDisposable(d)
//                if (!NetWorkUtil.isNetworkConnected(BaseApplication.getAppContext())) {
//                    view?.showError(BaseApplication.getAppContext().resources.getString(R.string.network_unavailable_tip))
//                    onComplete()
//                }
            }

            override fun onNext(t: T) {
                when {
                    t.code == Constants.CODE_200 -> onSuccess.invoke(
                        t
                    )
                    else -> {
                        view?.showError(t.message)
                    }
                }
            }

            override fun onError(t: Throwable) {
                view?.hideLoading()
                view?.showError(t.message)
            }
        })
}

fun Throwable.resolve() :ExceptionWrapper {
    return if (this is ExceptionWrapper) {
        this
    }else {
        ExceptionWrapper(ERROR_MSG_UNKNOWN, ERROR_CODE_UNKNOWN)
    }
}
