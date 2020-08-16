package com.asurint.slug.service

import com.asurint.slug.api.Slug
import org.springframework.stereotype.Service

interface SlugService {
	fun getAllSlugs(): List<Slug>
	fun getSlug(slugId: String): Slug
	fun addSlug(url: String, description: String): Slug
	fun updateSlug(slugId: String, url: String): Slug
	fun deleteSlug(slugId: String)
}

@Service
class SlugServiceImpl : SlugService {
	override fun getAllSlugs(): List<Slug> {
		TODO("Not yet implemented")
	}

	override fun getSlug(slugId: String): Slug {
		TODO("Not yet implemented")
	}

	override fun addSlug(url: String, description: String): Slug {
		TODO("Not yet implemented")
	}

	override fun updateSlug(slugId: String, url: String): Slug {
		TODO("Not yet implemented")
	}

	override fun deleteSlug(slugId: String) {
		TODO("Not yet implemented")
	}
}