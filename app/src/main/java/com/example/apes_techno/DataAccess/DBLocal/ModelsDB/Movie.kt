package com.example.apes_techno.DataAccess.DBLocal.ModelsDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.apes_techno.Models.BaseModel

@Entity
data class Movie (
    @PrimaryKey
    @ColumnInfo(name = "Id")            var id                  : Int? = null,
    @ColumnInfo(name = "Name")          var name                : String? = null,
    @ColumnInfo(name = "Rating")        var rating              : String? = null,
    @ColumnInfo(name = "Date")          var release_date        : String? = null,
    @ColumnInfo(name = "Runtime")       var runtime             : String? = null,
    @ColumnInfo(name = "TotalRevenue")  var total_revenue       : String? = null,
    @ColumnInfo(name = "BoxRevenue")    var box_office_revenue  : String? = null,
    @ColumnInfo(name = "Budget")        var budget              : String? = null,
    @ColumnInfo(name = "Description")   var description         : String? = null,
    @ColumnInfo(name = "SiteDetailUrl") var site_detail_url     : String? = null,
    @ColumnInfo(name = "ApiDetailUrl")  var api_detail_url      : String? = null,

    @Ignore
    var image               : Images? = null,
    @Ignore
    var producers           : MutableList<Item>? = null,
    @Ignore
    var studios             : MutableList<Item>? = null,
    @Ignore
    var writers             : MutableList<Item>? = null
): BaseModel()