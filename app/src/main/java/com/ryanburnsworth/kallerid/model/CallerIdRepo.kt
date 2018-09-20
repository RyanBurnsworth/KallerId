package com.ryanburnsworth.kallerid.model

import com.ryanburnsworth.kallerid.util.Config
import io.reactivex.Observable

class CallerIdRepo(private val callerIdService : CallerIdService) {
    fun getCallerInfo(number : String) : Observable<DataModel.Result> {
        return callerIdService.getCallerInfo(Config.API_KEY, number)
    }
}