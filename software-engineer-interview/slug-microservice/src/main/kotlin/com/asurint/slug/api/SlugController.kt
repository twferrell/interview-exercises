package com.asurint.slug.api

import com.asurint.slug.loggerFor
import com.asurint.slug.service.SlugService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SlugController(val slugService: SlugService) {
	private val logger = loggerFor(javaClass)

	@GetMapping("/slugs", produces = [APPLICATION_JSON_VALUE])
	fun getAllSlugs(): ResponseEntity<List<Slug>> {
		val slugList = slugService.getAllSlugs()
		logger.info("API requested for all slugs")
		return ResponseEntity(slugList, HttpStatus.OK)
	}

	@GetMapping("/slugs/{slugId}", produces = [APPLICATION_JSON_VALUE])
	fun getSlug(@PathVariable slugId: String): ResponseEntity<Slug> {
		val slug = slugService.getSlug(slugId)
		logger.info("API requested for slugId: $slugId")
		return ResponseEntity(slug, HttpStatus.OK)
	}

	@PostMapping("/slugs", produces = [APPLICATION_JSON_VALUE])
	fun addSlug(@RequestBody request: SlugCreateRequest): ResponseEntity<Slug> {
		val slug = slugService.addSlug(request.url, request.description)
		logger.info("API created slug: $slug")
		return ResponseEntity(slug, HttpStatus.OK)
	}

	@PatchMapping("/slugs/{slugId}", produces = [APPLICATION_JSON_VALUE])
	fun updateSlug(@PathVariable slugId: String, @RequestBody request: SlugUpdateRequest): ResponseEntity<Slug> {
		val slug = slugService.updateSlug(slugId, request.url)
		logger.info("API requested to update slugId: $slugId")
		return ResponseEntity(slug, HttpStatus.OK)
	}

	@DeleteMapping("/slugs/{slugId}", produces = [APPLICATION_JSON_VALUE])
	fun deleteSlug(@PathVariable slugId: String): ResponseEntity<Void> {
		slugService.deleteSlug(slugId)
		logger.info("API requested to delete slugId: $slugId")
		return ResponseEntity(HttpStatus.NO_CONTENT)
	}
}

