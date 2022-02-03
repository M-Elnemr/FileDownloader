package com.nagwa.filedownloader.base.network.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class FileResponseDto(
    @Json(name = "id") val id: Int? = 0,
    @Json(name = "type") val type: String? = "",
    @Json(name = "url") val url: String? = "",
    @Json(name = "name") val name: String? = "",
)



