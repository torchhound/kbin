package co.whiteboarddynamics.demo.kbin.models

import com.google.gson.annotations.SerializedName

data class DeletePasteResponse(
  @SerializedName("success") val success: Boolean
)