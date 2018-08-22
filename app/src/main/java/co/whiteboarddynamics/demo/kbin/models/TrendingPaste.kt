package co.whiteboarddynamics.demo.kbin.models
import com.google.gson.annotations.SerializedName

data class TrendingPaste(
  @SerializedName("paste") val paste: Paste
)

data class Paste(
  @SerializedName("paste_key") val pasteKey: String,
  @SerializedName("paste_date") val pasteDate: String,
  @SerializedName("paste_title") val pasteTitle: String,
  @SerializedName("paste_size") val pasteSize: String,
  @SerializedName("paste_expire_date") val pasteExpireDate: String,
  @SerializedName("paste_private") val pastePrivate: String,
  @SerializedName("paste_format_long") val pasteFormatLong: String,
  @SerializedName("paste_format_short") val pasteFormatShort: String,
  @SerializedName("paste_url") val pasteUrl: String,
  @SerializedName("paste_hits") val pasteHits: String
)