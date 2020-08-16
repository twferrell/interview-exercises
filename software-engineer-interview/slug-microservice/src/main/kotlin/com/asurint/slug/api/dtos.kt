package com.asurint.slug.api

data class SlugCreateRequestDto(
	val url: String,
	val description: String
)

data class SlugUpdateRequestDto(
	val url: String
)

data class SlugDto(
	val id: String,
	val url: String,
	val descriptions: List<String>
)