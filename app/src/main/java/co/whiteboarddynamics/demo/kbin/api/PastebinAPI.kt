package co.whiteboarddynamics.demo.kbin.api

import co.whiteboarddynamics.demo.kbin.models.TrendingPaste
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface PastebinAPI {
    @POST("api_post.php?api_option=trends")
    fun postTrending(@Query("api_dev_key") apiKey: String): Observable<Array<TrendingPaste>>

    companion object {
        const val END_POINT = "https://pastebin.com/api/"

        fun create(): PastebinAPI {
            val gson = GsonBuilder()
                    .create()

            val client = OkHttpClient.Builder().addInterceptor { chain ->
                val newRequest = chain.request()
                        .newBuilder()
                        .addHeader("Accept", "application/json")
                        .build()
                chain.proceed(newRequest)
            }.build()

            val retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(PastebinAPI.END_POINT)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit.create(PastebinAPI::class.java)
        }
    }
}