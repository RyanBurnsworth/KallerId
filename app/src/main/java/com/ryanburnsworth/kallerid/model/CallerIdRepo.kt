package com.ryanburnsworth.kallerid.model

import com.ryanburnsworth.kallerid.util.Config
import io.reactivex.Observable

class CallerIdRepo {
    fun getCallerInfo(number : String) : Observable<DataModel.Result> {
        return CallerIdService.create().getCallerInfo(Config.API_KEY, number)
    }
}