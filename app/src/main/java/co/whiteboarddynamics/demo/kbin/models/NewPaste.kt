package co.whiteboarddynamics.demo.kbin.models

import com.google.gson.annotations.SerializedName

data class NewPaste(
  @SerializedName("sections") val sections: MutableList<Sections>
)

data class Sections(
  @SerializedName("name") val name: String,
  @SerializedName("syntax") val syntax: String?,
  @SerializedName("contents") val contents: String
)