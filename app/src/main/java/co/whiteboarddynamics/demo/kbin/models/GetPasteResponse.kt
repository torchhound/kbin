package co.whiteboarddynamics.demo.kbin.models

import com.google.gson.annotations.SerializedName

data class GetPasteResponse(
  @SerializedName("success") val success: Boolean,
  @SerializedName("paste") val paste: GetPaste
)

data class GetPaste(
  @SerializedName("id") val id: String,
  @SerializedName("encrypted") val encrypted: Boolean,
  @SerializedName("description") val description: String,
  @SerializedName("views") val views: Int,
  @SerializedName("created_at") val createdAt: String,
  @SerializedName("expires_at") val expiresAt: Any?,
  @SerializedName("sections") val sections: List<GetSection>
)

data class GetSection(
  @SerializedName("id") val id: Int,
  @SerializedName("syntax") val syntax: String,
  @SerializedName("name") val name: String,
  @SerializedName("contents") val contents: String
)