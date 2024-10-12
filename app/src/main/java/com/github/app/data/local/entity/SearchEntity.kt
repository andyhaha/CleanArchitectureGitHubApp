package com.github.app.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.github.app.domain.model.SearchItem

@Entity(
    tableName = "search_history",
    indices = [Index(value = ["content"], unique = true)]
)
data class SearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val content: String
)

fun SearchEntity.asDomainModel(): SearchItem {
    return SearchItem(
        id = id,
        content = content
    )
}
