import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.text.TextUtils
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.fragment.app.Fragment
import com.fhhy.phoenix.R
import com.fhhy.phoenix.toast.ToastUtil
import com.fhhy.phoenix.utils.FormatUtil
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit

/**
 * Created by admin on 2020/6/7.
 */
/**
 * Log
 */
fun Any.loge(content: String?) {
    loge(this.javaClass.simpleName, content ?: "")
}

fun loge(tag: String, content: String?) {
    Log.e(tag, content ?: "")
}

fun Fragment.showToast(content: String?) {
    ToastUtil.show(content)
}

fun Activity.showToast(content: String?) {
    ToastUtil.show(content)
}

fun Context.showToast(content: String?) {
    ToastUtil.show(content)
}


/**
 * 将手机号转为中间四位为*号
 */
fun String?.dealMobile(): String {
    if (this == null || TextUtils.isEmpty(this)) {
        return ""
    }
    val stringBuilder = StringBuilder()
    for (i in this.indices) {
        if (i in 3..6) {
            stringBuilder.append("*")
        } else {
            stringBuilder.append(this[i])
        }
    }
    return stringBuilder.toString()
}

/**
 * 页面多个view的点击事件
 */
fun Context.setViewClickListener(listener: View.OnClickListener, vararg views: View) {
    for (it in views) {
        it.setOnClickListener(listener)
    }
}

/**
 * 页面多个view的点击事件
 */
fun Fragment.setViewClickListener(listener: View.OnClickListener, vararg views: View) {
    for (it in views) {
        it.setOnClickListener(listener)
    }
}

fun Float.format(): String = FormatUtil.getNumberFormat().format(this)

inline fun View.noDoubleClick(crossinline clickAction: () -> Unit): Disposable =
    clicks().throttleFirst(400, TimeUnit.MILLISECONDS)
        .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
        .subscribe { clickAction() }

val Float.dp: Float                 // [xxhdpi](360 -> 1080)
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )

val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
    ).toInt()

//获取颜色 通过boolean值 是涨还是跌
fun Context.getTextColor(up: Boolean): Int =
    if (up)
        resources.getColor(R.color.currency_up_color)
    else
        resources.getColor(R.color.currency_down_color)
