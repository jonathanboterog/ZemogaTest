package com.zemoga.mobiletest.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity (

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "name")
    var name : String = "",

    @ColumnInfo(name = "email")
    var email : String = "",

    @ColumnInfo(name = "phone")
    var phone : String = "",

    @ColumnInfo(name = "website")
    var website : String = "",

)