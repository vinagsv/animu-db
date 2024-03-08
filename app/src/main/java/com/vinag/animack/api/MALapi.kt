package com.vinag.animack.api

import com.vinag.animack.models.anime.AnimeResponse
//import com.fangzsx.animu_db.models.animecharacters.AnimeCharactersResponse
//import com.fangzsx.animu_db.models.review.ReviewResponse
//import com.fangzsx.animu_db.models.character.CharacterResponse
//import com.fangzsx.animu_db.models.manga.MangaResponse
import com.vinag.animack.models.popular.PopularAnimeResponse
import com.vinag.animack.models.recommendation.RecommendationsResponse
import com.vinag.animack.models.searchAnime.SearchAnimeResponse
//import com.fangzsx.animu_db.models.searchManga.SearchMangaResponse
//import com.fangzsx.animu_db.models.topcharacters.TopCharactersResponse
//import com.fangzsx.animu_db.models.topmanga.TopMangaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MALapi {

    //anime
    @GET("v4/recommendations/anime")
    suspend fun getAnimeRecommendation() : Response<RecommendationsResponse>

    @GET("v4/anime/{id}")
    suspend fun getAnimeByID(
        @Path("id")
        id : Int
    ) : Response<AnimeResponse>


    @GET("v4/top/anime")
    suspend fun getPopularAnime() : Response<PopularAnimeResponse>


    @GET("v4/anime?")
    suspend fun searchAnimeTitlesByQuery(
        @Query("q")
        title : String
    ) : Response<SearchAnimeResponse>




}