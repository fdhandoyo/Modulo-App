package org.d3if3019.modulo.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3019.modulo.model.HasilInfo
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://raw.githubusercontent.com/fdhandoyo/API/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ModulusApiService {
    @GET("modulusAPI.json")
    suspend fun getInfo(): List<HasilInfo>
}

object InfoApi {
    val service: ModulusApiService by lazy {
        retrofit.create(ModulusApiService::class.java)
    }

    fun getInfoUrl(imageId: String): String {
        return "$BASE_URL$imageId.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED}

