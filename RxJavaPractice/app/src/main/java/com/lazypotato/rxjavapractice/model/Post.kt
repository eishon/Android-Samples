package com.lazypotato.rxjavapractice.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @ColumnInfo var userId: String,
    @PrimaryKey var id: Int,
    @ColumnInfo var title: String,
    @ColumnInfo var body: String
)
