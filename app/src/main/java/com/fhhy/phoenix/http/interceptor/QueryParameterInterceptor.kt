import android.os.Build
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.nio.charset.Charset


/**
 * Created by hesanwei on 2020/1/13.
 * 设置公共参数
 */
class QueryParameterInterceptor : Interceptor {
    private val UTF8 = Charset.forName("UTF-8")

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request: Request
        val timestamp: String = System.currentTimeMillis().toString()
        val modifiedUrl = originalRequest.url().newBuilder()
            // Provide your custom parameter here
//            .setEncodedQueryParameter("clientId", Constants.CLIENT_ID)
//            .setEncodedQueryParameter("timestamp", timestamp)
//            .setEncodedQueryParameter("dataType", Constants.DATA_TYPE)
//            .setEncodedQueryParameter("sign", sign)
//            .setEncodedQueryParameter("deviceSysVersion", AMDeviceUtils.getOS())
//            .setEncodedQueryParameter(
//                "loginToken",
//                AMSPUtils.getLoginToken(BaseApplication.getAppContext().applicationContext)
//            )
//            .setEncodedQueryParameter("platform", "Android" + Build.VERSION.SDK_INT)
//            .setEncodedQueryParameter("appVersion", BuildConfig.VERSION_NAME)
            .build()
        request = originalRequest.newBuilder().url(modifiedUrl).build()
        return chain.proceed(request)
    }

}