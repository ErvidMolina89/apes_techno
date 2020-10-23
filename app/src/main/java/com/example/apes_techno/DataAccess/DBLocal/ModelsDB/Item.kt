package com.example.apes_techno.DataAccess.DBLocal.ModelsDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.apes_techno.Models.BaseModel

@Entity
data class Item (
    @PrimaryKey
    @ColumnInfo(name = "Id")            var id              : Int? = null,
    @ColumnInfo(name = "Name")          var name            : String? = null,
    @ColumnInfo(name = "Type")          var type            : String? = null,
    @ColumnInfo(name = "SiteDetailUrl") var site_detail_url : String? = null,
    @ColumnInfo(name = "ApiDetailUrl")  var api_detail_url  : String? = null
): BaseModel()