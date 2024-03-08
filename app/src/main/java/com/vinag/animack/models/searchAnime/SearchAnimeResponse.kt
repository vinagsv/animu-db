package com.vinag.animack.models.searchAnime

data class SearchAnimeResponse(
    val `data`: List<Data>,
    val pagination: Pagination
)