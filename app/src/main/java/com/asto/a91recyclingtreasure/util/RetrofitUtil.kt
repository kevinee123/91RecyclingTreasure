package com.asto.a91recyclingtreasure.util

import com.asto.a91recyclingtreasure.core.Common
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtil {

    /**
     * 设置拦截器
     *
     * @param interceptor
     * @return
     */
    fun setInterceptor(interceptor: Interceptor): OkHttpClient.Builder {
        return httpBuilder.addInterceptor(interceptor)
    }

    companion object {

        private var isAdded = false
        private var retrofit: Retrofit? = null
        protected var retrofitBuilder = Retrofit.Builder()
        protected var httpBuilder = OkHttpClient.Builder()


        private fun init() {
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
                .client(httpBuilder.build())
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
