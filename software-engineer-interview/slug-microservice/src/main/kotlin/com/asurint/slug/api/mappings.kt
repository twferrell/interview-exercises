package com.asurint.slug.api

import com.asurint.slug.domain.Slug

/**
 * Extension method useful for converting
 * a backend domain entity object into a DTO.
 *
 * @return the DTO representation of the domain object.
 */

fun Slug.toDto() = SlugDto(
	this.id,
	this.url,
	this.descriptions.map { it.description }
)