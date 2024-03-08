package com.vinag.animack.viewmodels.anime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinag.animack.models.recommendation.Data
import com.vinag.animack.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class AnimeFragmentViewModel : ViewModel() {

    val animeRecommendations : MutableLiveData<List<Data>> = MutableLiveData()
    val animePopular : MutableLiveData<List<com.vinag.animack.models.popular.Data>> = MutableLiveData()

    private val _anime : MutableLiveData<com.vinag.animack.models.anime.Data> = MutableLiveData()
    val anime : LiveData<com.vinag.animack.models.anime.Data> = _anime



    init {
        getAnimeRecommendations()
        getPopularAnime()


    }

    private fun getAnimeRecommendations() = viewModelScope.launch{
        val response = RetrofitInstance.malAPI.getAnimeRecommendation()
        if(response.isSuccessful){
            response.body()?.let{ list->
                animeRecommendations.postValue(list.data)

            }
        }
    }

    fun getAnimeByID(id : Int) = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getAnimeByID(id)
        if(response.isSuccessful){
            response.body()?.let { animeResponse ->
               _anime.postValue(animeResponse.data)
            }
        }
    }


    private fun getPopularAnime() = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getPopularAnime()
        if(response.isSuccessful){
            response.body()?.let { list ->
                animePopular.postValue(list.data.subList(0,25))
            }
        }
    }

}