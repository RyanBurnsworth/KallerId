package com.ryanburnsworth.kallerid.model

object DataModel {
    data class Result(var id : String, var belongs_to : ArrayList<BelongsTo>)
}