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

        builder.addHeader("Content-type", "application/json; charset=utf-8")
            .addHeader("authentication", SPUtils.getString(SPKeyConstants.SP_KEY_TOKEN))
            .addHeader("IMEI", DeviceUtils.getDeviceId(BaseApplication.getAppContext()))

        return chain.proceed(builder.build())
    }

}