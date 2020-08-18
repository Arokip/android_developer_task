package cz.arokip.androiddevelopertask.api

import cz.arokip.androiddevelopertask.data.Position
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


interface GitHubJobsApi {

    @GET("positions.json")
    suspend fun getAllPositions(): List<Position>

    companion object {

        fun makeRetrofitService(baseUrl: String): GitHubJobsApi {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(GitHubJobsApi::class.java)
        }
    }

}