import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.fhhy.phoenix.toast.ToastUtil
import java.lang.StringBuilder

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
