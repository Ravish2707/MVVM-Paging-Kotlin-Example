package com.ravish.paging3_mvvm_kotlin.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ravish.paging3_mvvm_kotlin.model.Dogs
import com.ravish.paging3_mvvm_kotlin.networking.DogApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DogPagingSourceRepository constructor(private val dogApiService: DogApiService) :PagingSource<Int, Dogs>() {

    override fun getRefreshKey(state: PagingState<Int, Dogs>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dogs> {
        val page = params.key ?: 1

        return try {
            val response = dogApiService.getAllDogs(page, params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}