package co.whiteboarddynamics.demo.kbin.models

import com.google.gson.annotations.SerializedName

data class NewPaste(
  @SerializedName("sections") val sections: MutableList<Sections>,
  @SerializedName("description") val description: String?,
  @SerializedName("encrypted") val encrypted: Boolean?
)

data class Sections(
  @SerializedName("name") val name: String,
  @SerializedName("syntax") val syntax: String?,
  @SerializedName("contents") val contents: String
)