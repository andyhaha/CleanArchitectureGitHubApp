package com.andy.github.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andy.github.home.data.local.entity.SearchEntity
import javax.inject.Named

@Database(
    entities = [SearchEntity::class],
    version = 1,
    exportSchema = false
)
//@Named("test_db")
abstract class SearchHistoryDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
}