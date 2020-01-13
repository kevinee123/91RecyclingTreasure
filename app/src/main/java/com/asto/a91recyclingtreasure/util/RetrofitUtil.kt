package com.asto.a91recyclingtreasure.util

import android.util.Log
import com.asto.a91recyclingtreasure.core.Common
import com.asto.a91recyclingtreasure.core.MyApplication
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtil {


    companion object {

        private var isAdded = false
        private var retrofit: Retrofit? = null
        protected var retrofitBuilder = Retrofit.Builder()
        protected var httpBuilder = OkHttpClient.Builder()


        /**
         * 日志拦截器
         * 将你访问的接口信息
         *
         * @return 拦截器
         */
        //日志显示级别
        //新建log拦截器
        val loggerInterceptor: HttpLoggingInterceptor
            get() {
                val level = HttpLoggingInterceptor.Level.BODY
                val loggingInterceptor =
                    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.e("logger",message) })
                loggingInterceptor.level = level
                return loggingInterceptor
            }

        private fun init() {
            //设置拦截器
            httpBuilder.addInterceptor {
                val original = it.request()

                val request = original.newBuilder()
                    .header(Common.BEAN_TOKEN,ASimpleCache.get(MyApplication.application).getAsString(Common.BEAN_TOKEN)?:"")
                    .method(original.method(), original.body())
                    .build()

                 it.proceed(request)
            }
            retrofitBuilder
                //设置转换器(返回类型)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .setLenient()
                            .create()
                    )
                )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpBuilder.addInterceptor(loggerInterceptor).build())
                .baseUrl(Common.BASE_URL)
            isAdded = !isAdded
        }

        /**
         * 构建retroft
         *
         * @return Retrofit对象
         */
        fun getRetrofit(): Retrofit {
            if (!isAdded)
                init()
            if (retrofit == null) {
                //锁定代码块
                synchronized(RetrofitUtil::class.java) {
                    if (retrofit == null) {
                        retrofit = retrofitBuilder.build() //创建retrofit对象
                    }
                }
            }
            return retrofit ?: retrofitBuilder.build()

        }
    }

}
