package com.ryanburnsworth.kallerid.model

import com.ryanburnsworth.kallerid.util.Config
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CallerIdService {

    @GET("phone.json")
    fun getCallerInfo(@Query("api_key") apiKey: String, @Query("phone") phoneNumber: String)
            : Observable<DataModel.Result>

    companion object {
        fun create(): CallerIdService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(CallerIdService::class.java)
        }
    }
}