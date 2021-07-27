package com.lazypotato.mvvm_hilt_flow_room.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ClickEntityDao {
    @Query("SELECT * FROM ClickEntity ORDER BY type")
    fun getAll(): List<ClickEntity>

    @Query("SELECT * FROM ClickEntity WHERE type=:type")
    fun getByType(type: String): ClickEntity

    @Query("UPDATE ClickEntity SET clickCount = clickCount + 1, clickTime = CURRENT_TIMESTAMP WHERE type=:type")
    fun incrementClick(type: String)

    @Query("DELETE FROM ClickEntity")
    fun clear()
}