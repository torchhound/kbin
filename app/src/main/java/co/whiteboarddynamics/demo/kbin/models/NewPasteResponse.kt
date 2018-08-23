package co.whiteboarddynamics.demo.kbin.models

import com.google.gson.annotations.SerializedName

data class NewPasteResponse(
  @SerializedName("id") val id: String,
  @SerializedName("link") val link: String
)