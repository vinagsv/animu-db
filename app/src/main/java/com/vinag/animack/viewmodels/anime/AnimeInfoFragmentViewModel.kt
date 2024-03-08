package com.vinag.animack.viewmodels.anime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinag.animack.models.anime.Data
import com.vinag.animack.models.youtube.Item
import com.vinag.animack.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class AnimeInfoFragmentViewModel : ViewModel() {
    var anime : MutableLiveData<Data> = MutableLiveData()

    private val _youtubeID : MutableLiveData<List<Item>> = MutableLiveData()
    val youtubeID : LiveData<List<Item>> = _youtubeID


    fun getAnimeById(id : Int) = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getAnimeByID(id)
        if(response.isSuccessful){
            response.body()?.let{ animeData ->
                anime.postValue(animeData.data)
            }
        }
    }

    fun getYoutubeTrailerID(title : String) = viewModelScope.launch {
        val response = RetrofitInstance.youtubeAPI.searchForTrailer(
            "snippet",
            1,
            "relevance",
            title,
            "none",
            "AIzaSyBrH7VTA4nFMix6UAZQ7-G8kwGfmyUx-4o"
        )

        if(response.isSuccessful){
            response.body()?.let { youtubeResponse ->
                _youtubeID.postValue(youtubeResponse.items)
            }
        }
    }

}