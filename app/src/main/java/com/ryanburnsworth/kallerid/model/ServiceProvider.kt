package com.ryanburnsworth.kallerid.model

object ServiceProvider {
    fun getServiceProvider() : CallerIdRepo {
        return CallerIdRepo(CallerIdService.create())
    }
}