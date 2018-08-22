package co.whiteboarddynamics.demo.kbin.models

import com.google.gson.annotations.SerializedName

data class Paste(
  @SerializedName("total") val total: Int,
  @SerializedName("per_page") val perPage: Int,
  @SerializedName("current_page") val currentPage: Int,
  @SerializedName("last_page") val lastPage: Int,
  @SerializedName("next_page_url") val nextPageUrl: String,
  @SerializedName("prev_page_url") val prevPageUrl: String,
  @SerializedName("from") val from: Int,
  @SerializedName("to") val to: Int,
  @SerializedName("data") val data: List<Data>
)

data class Data(
  @SerializedName("id") val id: String,
  @SerializedName("description") val description: String,
  @SerializedName("views") val views: Int,
  @SerializedName("created_at") val createdAt: String,
  @SerializedName("sections") val sections: List<Section>
)

data class Section(
  @SerializedName("name") val name: String,
  @SerializedName("syntax") val syntax: String,
  @SerializedName("contents") val contents: String
)