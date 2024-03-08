package com.vinag.animack.models.popular

data class PopularAnimeResponse(
    val `data`: List<Data>,
    val pagination: Pagination
)