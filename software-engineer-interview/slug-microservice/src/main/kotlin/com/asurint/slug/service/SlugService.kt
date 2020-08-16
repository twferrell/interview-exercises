package com.asurint.slug.service

import com.asurint.slug.converter.SlugConverter
import com.asurint.slug.domain.Slug
import com.asurint.slug.domain.SlugDescription
import com.asurint.slug.domain.SlugRepository
import com.asurint.slug.exceptions.ResourceNotFoundException
import org.springframework.stereotype.Service

interface SlugService {
	fun getAllSlugs(): List<Slug>
	fun getSlug(slugId: String): Slug
	fun addSlug(url: String, description: String): Slug
	fun updateSlug(slugId: String, url: String): Slug
	fun deleteSlug(slugId: String)
}

@Service
class SlugServiceImpl(
	val slugRepository: SlugRepository,
	val slugConverter: SlugConverter
) : SlugService {
	override fun getAllSlugs(): List<Slug> = slugRepository.findAll()

	override fun getSlug(slugId: String): Slug {
		return slugRepository.findById(slugId).orElseThrow {
			ResourceNotFoundException("Slug $slugId was not found!")
		}
	}

	override fun addSlug(url: String, description: String): Slug {
		val slugId = slugConverter.convert(description)
		val optional = slugRepository.findById(slugId)

		val slug = if (optional.isPresent) {
			optional.get().apply { addDescription(description) }
		} else {
			Slug(slugId, url, mutableSetOf(SlugDescription(slugId, description)))
		}
		return slugRepository.save(slug)
	}

	override fun updateSlug(slugId: String, url: String): Slug {
		val slug = getSlug(slugId)
		slug.url = url
		return slugRepository.save(slug)
	}

	override fun deleteSlug(slugId: String) = slugRepository.findById(slugId).ifPresent { slugRepository.delete(it) }
}