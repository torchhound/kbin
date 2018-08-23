package co.whiteboarddynamics.demo.kbin.api

import co.whiteboarddynamics.demo.kbin.models.NewPaste
import co.whiteboarddynamics.demo.kbin.models.NewPasteResponse
import co.whiteboarddynamics.demo.kbin.models.Paste
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PasteAPI {
  @GET("pastes?perpage=25")
  fun getAll(): Observable<Paste>

  @POST("pastes")
  fun postNew(@Body newPaste: NewPaste): Observable<NewPasteResponse>

  companion object {
    const val END_POINT = "https://api.paste.ee/v1/"

    fun create(apiKey: String): PasteAPI {
      val gson = GsonBuilder().create()

      val client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest = chain.request()
              .newBuilder()
              .addHeader("Accept", "application/json")
              .addHeader("X-Auth-Token", apiKey)
              .build()
        chain.proceed(newRequest)
      }.build()

      val retrofit = Retrofit.Builder()
          .client(client)
          .baseUrl(PasteAPI.END_POINT)
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build()

      return retrofit.create(PasteAPI::class.java)
    }
  }
}