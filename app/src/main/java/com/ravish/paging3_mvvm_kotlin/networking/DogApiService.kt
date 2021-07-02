package com.ravish.paging3_mvvm_kotlin.networking

import com.ravish.paging3_mvvm_kotlin.model.Dogs
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApiService {

    @GET("images/search")
    suspend fun getAllDogs(@Query("page") page:Int, @Query("limit") limit:Int) : List<Dogs>

    companion object {
        const val BASE_URL = "https://api.thedogapi.com/v1/";
    }
}