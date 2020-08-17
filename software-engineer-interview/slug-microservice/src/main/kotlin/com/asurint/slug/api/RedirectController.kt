package com.asurint.slug.api

import com.asurint.slug.loggerFor
import com.asurint.slug.service.SlugService
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpServletResponse

/**
 * This controller will send back a location redirect based on the
 * url property found on the slug.
 *
 * @property slugService is used to lookup a slug based on its ID
 */
@Controller
class RedirectController(val slugService: SlugService) {
	private val logger = loggerFor(javaClass)

	@GetMapping("/redirect/{slugId}", produces = [MediaType.APPLICATION_JSON_VALUE])
	fun getSlug(@PathVariable slugId: String, response: HttpServletResponse) {
		val slug = slugService.getSlug(slugId)
		logger.info("Redirect requested for slugId: $slugId")
		response.apply {
			setHeader("Location", slug.url)
			status = 302
		}
	}
}