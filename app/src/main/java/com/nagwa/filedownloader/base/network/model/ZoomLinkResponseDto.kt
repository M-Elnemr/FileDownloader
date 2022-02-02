package com.nagwa.filedownloader.base.network.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class FileResponseDto(
    @Json(name = "dummy") val dummy: String? = "",
)



