package com.vinag.animack.models.anime

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "anime"
)

data class Data(
    @PrimaryKey(autoGenerate = true)
    var id: Int= 0,
    val aired: Aired,
    val airing: Boolean,
    val background: String,
    val broadcast: Broadcast,
    val demographics: List<Any>,
    val duration: String,
    val episodes: Int,
    val explicit_genres: List<Any>,
    val favorites: Int,
    val genres: List<Genre>,
    val images: Images,
    val licensors: List<Licensor>,
    val mal_id: Int,
    val members: Int,
    val popularity: Int,
    val producers: List<Producer>,
    val rank: Int,
    val rating: String,
    val score: Double,
    val scored_by: Int,
    val season: Any,
    val source: String,
    val status: String,
    val studios: List<Studio>,
    val synopsis: String,
    val themes: List<Any>,
    val title: String,
    val title_english: String,
    val title_japanese: String,
    val title_synonyms: List<Any>,
    val trailer: Trailer,
    val type: String,
    val url: String,
    val year: Any
)