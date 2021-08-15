package com.zemoga.mobiletest.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class CommentEntity (

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "postId")
    var postId : Int = 0,

    @ColumnInfo(name = "name")
    var name : String = "",

    @ColumnInfo(name = "email")
    var email : String = "",

    @ColumnInfo(name = "body")
    var body : String = "",
)