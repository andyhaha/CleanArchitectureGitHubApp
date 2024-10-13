package com.andy.github.home.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andy.github.home.data.local.entity.SearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM search_history ORDER BY id DESC")
    fun getAllSearchRecords(): Flow<List<SearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(record: SearchEntity)

    @Delete
    suspend fun deleteSearch(content: SearchEntity)

    @Query("DELETE FROM search_history")
    suspend fun deleteAllSearchRecords()
}