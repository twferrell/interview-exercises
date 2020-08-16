package com.asurint.slug.api

import com.asurint.slug.loggerFor
import com.asurint.slug.service.SlugService
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpServletResponse

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