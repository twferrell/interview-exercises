package com.asurint.slug.api

import com.asurint.slug.domain.Slug
import com.asurint.slug.loggerFor
import com.asurint.slug.service.SlugService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * This controller supports a set of RESTful APIs allowing
 * the consumer to perform a basic set of CRUD operations
 * on slug-resources.
 *
 * @property slugService used for handling the lookups and mutations to the underlying slug resources
 */
@RestController
class SlugController(val slugService: SlugService) {
	private val logger = loggerFor(javaClass)

	@GetMapping("/slugs", produces = [APPLICATION_JSON_VALUE])
	fun getAllSlugs(): ResponseEntity<List<SlugDto>> {
		val slugList = slugService.getAllSlugs().map(Slug::toDto)

		logger.info("API requested for all slugs")
		return ResponseEntity(slugList, HttpStatus.OK)
	}

	@GetMapping("/slugs/{slugId}", produces = [APPLICATION_JSON_VALUE])
	fun getSlug(@PathVariable slugId: String): ResponseEntity<SlugDto> {
		val slug = slugService.getSlug(slugId)

		logger.info("API requested for slugId: $slugId")
		return ResponseEntity(slug.toDto(), HttpStatus.OK)
	}

	@PostMapping("/slugs", produces = [APPLICATION_JSON_VALUE])
	fun addSlug(@RequestBody request: SlugCreateRequestDto): ResponseEntity<SlugDto> {
		val slug = slugService.addSlug(request.url, request.description).toDto()

		logger.info("API created slug: $slug")
		return ResponseEntity(slug, HttpStatus.OK)
	}

	@PostMapping("/slugs/{slugId}", produces = [APPLICATION_JSON_VALUE])
	fun updateSlug(@PathVariable slugId: String, @RequestBody request: SlugUpdateRequestDto): ResponseEntity<SlugDto> {
		val slug = slugService.updateSlug(slugId, request.url).toDto()

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

