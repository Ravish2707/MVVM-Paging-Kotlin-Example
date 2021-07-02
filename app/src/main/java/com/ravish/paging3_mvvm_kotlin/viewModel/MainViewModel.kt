package com.ravish.paging3_mvvm_kotlin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ravish.paging3_mvvm_kotlin.model.Dogs
import com.ravish.paging3_mvvm_kotlin.networking.DogApiService
import com.ravish.paging3_mvvm_kotlin.repository.DogPagingSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dogApiService: DogApiService): ViewModel() {

    fun getAllDogs() : Flow<PagingData<Dogs>> = Pager(
        config = PagingConfig(20, enablePlaceholders = false),
    ){
        DogPagingSourceRepository(dogApiService)
    }.flow.cachedIn(viewModelScope)
}