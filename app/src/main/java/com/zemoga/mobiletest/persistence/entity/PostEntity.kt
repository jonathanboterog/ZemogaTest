package com.zemoga.mobiletest.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
class PostEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "title")
    var title : String = "",

    @ColumnInfo(name = "body")
    var body : String = "",

    @ColumnInfo(name = "userId")
    var userId : Int = 0,

)