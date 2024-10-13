package com.andy.github.home.domain.model

import com.andy.github.home.data.local.entity.SearchEntity

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