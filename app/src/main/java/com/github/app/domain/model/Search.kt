package com.github.app.domain.model

import com.github.app.data.local.entity.SearchEntity

data class SearchItem(
    val id: Int = 0,
    val content: String
)

fun SearchItem.asEntity(): SearchEntity {
    return SearchEntity(
        id = id,
        content = content
    )
}