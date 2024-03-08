package com.vinag.animack.models.recommendation

data class RecommendationsResponse(
    val `data`: List<Data>,
    val pagination: Pagination
)