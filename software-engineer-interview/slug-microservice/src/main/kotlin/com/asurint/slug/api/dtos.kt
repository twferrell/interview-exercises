package com.asurint.slug.api

data class SlugCreateRequest(
	val url: String,
	val description: String
)

data class SlugUpdateRequest(
	val url: String
)

data class Slug(
	val id: String,
	val url: String,
	val descriptions: List<String>
)