package com.asurint.slug.api

import com.asurint.slug.domain.Slug

fun Slug.toDto() = SlugDto(
	this.id,
	this.url,
	this.descriptions.map { it.description }
)