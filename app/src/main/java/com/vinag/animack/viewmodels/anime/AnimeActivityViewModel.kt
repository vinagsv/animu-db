package com.vinag.animack.viewmodels.anime

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinag.animack.models.anime.Data
import com.vinag.animack.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class AnimeActivityViewModel: ViewModel() {

    var anime : MutableLiveData<Data> = MutableLiveData()

    fun getAnimeById(id : Int) = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getAnimeByID(id)
        if(response.isSuccessful){
            response.body()?.let { animeData ->
                anime.postValue(animeData.data)
            }
        }
    }


}