import com.fhhy.phoenix.base.BaseApplication
import com.fhhy.phoenix.constants.SPKeyConstants
import com.fhhy.phoenix.utils.DeviceUtils
import com.fhhy.phoenix.utils.SPUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author chenxz
 * @date 2018/9/26
 * @desc HeaderInterceptor: 设置请求头
 */
class HeaderInterceptor : Interceptor {

    /**
     * Created by hesanwei on 2020/1/13.
     */

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val builder = request.newBuilder()
        val requestUrl = request.url().toString()
        builder.addHeader("Content-Type", "multipart/form-data")
            .addHeader("authentication", SPUtils.getString(SPKeyConstants.SP_KEY_TOKEN))
            .addHeader("IMEI", DeviceUtils.getDeviceId(BaseApplication.getAppContext()))

//        if (requestUrl.contains(Regex("get_check_code"))) {
        builder.addHeader("cookie", SPUtils.getString(SPKeyConstants.SP_KEY_COOKIE))
//        }
        return chain.proceed(builder.build())
    }

}