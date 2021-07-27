package com.lazypotato.mvvm_hilt_flow_room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClickEntity(
    @PrimaryKey
    val type: String,
    val clickCount: Int = 0,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val clickTime: Long = System.currentTimeMillis()
)