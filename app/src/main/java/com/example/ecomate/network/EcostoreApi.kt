package com.example.ecomate.network

import com.example.ecomate.model.MySurroundStoresResponse
import com.example.ecomate.model.StoreDto
import com.example.ecomate.model.StorePutBody
import com.example.ecomate.model.StoreResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface EcostoreApi {

    @GET("v1/ecoStores")
    suspend fun getEcoStores(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): MySurroundStoresResponse

    @GET("v1/ecoStores/members/location-like")
    suspend fun getLikedEcoStores(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): MySurroundStoresResponse

    @GET("v1/ecoStores/{storeId}")
    suspend fun getEcoStoreInfo(
        @Path("storeId") storeId: Int
    ): StoreResponse

    @Multipart
    @POST("v1/ecoStores")
    suspend fun postEcoStore(
        @Part("createDto") store: StoreDto,
        @Part file: MultipartBody.Part
    )

    @PUT("v1/ecoStores/{storeId}")
    suspend fun putEcoStore(
        @Path("storeId") storeId: Int,
        @Body storePutBody: StorePutBody
    )

    @DELETE("v1/ecoStores/{storeId}")
    suspend fun deleteEcoStore(
        @Path("storeId") storeId: Int
    )
}