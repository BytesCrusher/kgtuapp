package com.application.kgtuapp.di

import android.content.Context
import android.content.SharedPreferences
import com.application.kgtuapp.Network.ScheduleGoClient
import com.application.kgtuapp.R
import com.application.kgtuapp.screens.schedule.ScheduleGoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun httpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        sharedPreferences: SharedPreferences
    ): OkHttpClient =
        OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                val request = chain.request().newBuilder()
//                    .addHeader(
//                        "Authorization",
//                        "Bearer ${sharedPreferences.getString("API_KEY", "").orEmpty()}"
//                    )
//                    .build()
//
//                return@addInterceptor chain.proceed(request)
//            }
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    @ScheduleGoClient
    fun scheduleGoRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.kstuapp.ru")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideScheduleGoApi(@ScheduleGoClient retrofit: Retrofit): ScheduleGoApi =
        retrofit.create(ScheduleGoApi::class.java)
}