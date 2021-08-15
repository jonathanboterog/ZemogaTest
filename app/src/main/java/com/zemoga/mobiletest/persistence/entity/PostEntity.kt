package com.zemoga.mobiletest.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "title")
    var title : String = "",

    @ColumnInfo(name = "body")
    var body : String = "",

    @ColumnInfo(name = "userId")
    var userId : Int = 0,

    @ColumnInfo(name = "favorites")
    var favorite : Boolean = false,

    @ColumnInfo(name = "read")
    var read : Boolean = false,

)