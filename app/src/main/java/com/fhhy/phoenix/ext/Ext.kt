import android.app.Activity
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Resources
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.fhhy.phoenix.R
import com.fhhy.phoenix.constants.Constants
import com.fhhy.phoenix.toast.ToastUtil
import com.fhhy.phoenix.utils.FormatUtil
import com.fhhy.phoenix.utils.GlideEngine
import com.fhhy.phoenix.utils.ManifestParser
import com.huantansheng.easyphotos.EasyPhotos
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.disposables.Disposable
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
 * 是否是手机号
 */
fun String?.isMobile(): Boolean {
    if (this == null || this.length != 11) {
        return false
    }
    val regex = Regex("[1]\\d{10}") // "[1]"代表第1位为数字1，"\\d{10}"代表后面是可以是0～9的数字，有10位。
    return this.matches(regex)
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

//给text 设置下划线
fun AppCompatTextView.underline() {
//    paint.flags = Paint.UNDERLINE_TEXT_FLAG; //下划线
//    paint.isAntiAlias = true;//抗锯齿
    if (text.isNotEmpty()) {
        val spannableString = SpannableString(text.toString().trim())
        val underlineSpan = UnderlineSpan()
        spannableString.setSpan(
            underlineSpan,
            0,
            spannableString.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        text = spannableString
    }
}

fun TextView.underline() {
    if (text.isNotEmpty()) {
        val spannableString = SpannableString(text.toString().trim())
        val underlineSpan = UnderlineSpan()
        spannableString.setSpan(
            underlineSpan,
            0,
            spannableString.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        text = spannableString
    }

}


fun Any.getRequestMap(): MutableMap<String, String?> {
    return HashMap()
}

//币价 上涨还是下跌判断
val String.isUp: Boolean
    get() = "up" == this

/**
 * 从相册选图片
 */
fun AppCompatActivity.selectImageFromGallery(maxCount: Int = 1) {
    EasyPhotos.createAlbum(
        this,
        false,
        GlideEngine.getInstance()
    )
        .setCount(maxCount)
        .start(Constants.SELECT_IMAGE_REQUEST_CODE)
}

/**
 * 拍照
 */
fun AppCompatActivity.takePhoto() {
    EasyPhotos.createCamera(this)
        .setFileProviderAuthority(
            ManifestParser.getFileProviderAuthorities(
                this
            )
        )
        .start(Constants.SELECT_IMAGE_REQUEST_CODE)
}

fun String?.copyText(context: Context) {
    // 从API11开始android推荐使用android.content.ClipboardManager
// 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。

    // 从API11开始android推荐使用android.content.ClipboardManager
// 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
    if (TextUtils.isEmpty(this)) {
        return
    }
    val cm: ClipboardManager? =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
// 将文本内容放到系统剪贴板里。
// 将文本内容放到系统剪贴板里。
    cm?.text = this
    context.showToast(context.resources.getString(R.string.copy_success))
}

