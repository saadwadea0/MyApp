package com.easyapply.myapp.di

import androidx.room.Dao
import com.easyapply.myapp.common.Constants
import com.easyapply.myapp.common.Constants.BASE_URL
import com.easyapply.myapp.data.PostApi
import com.easyapply.myapp.data.repo.PostRepoImpl
import com.easyapply.myapp.db.PostDao
import com.easyapply.myapp.domain.repo.PostRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging).addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                val request = original.newBuilder().addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .method(original.method, original.body).build()
                chain.proceed(request)
            }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PostApi =
        retrofit.create(PostApi::class.java)


    @Provides
    @Singleton
    fun providePostApiService(api: PostApi,dao: PostDao): PostRepo {
        return PostRepoImpl(api,dao)
    }
}