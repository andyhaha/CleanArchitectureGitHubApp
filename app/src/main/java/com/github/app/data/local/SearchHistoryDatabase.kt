package com.github.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.app.data.local.entity.SearchEntity

@Database(
    entities = [SearchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SearchHistoryDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
}