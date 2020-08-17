package com.asurint.slug.api

import com.asurint.slug.service.SlugService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItem
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * These tests focus on the API layer/contracts and were written in a TDD-based fashion,
 * having the service details originally mocked out.
 */
@WebMvcTest(SlugController::class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MockedSlugControllerTests {
	@Autowired
	private lateinit var mockMvc: MockMvc

	@MockkBean
	private lateinit var slugServiceMock: SlugService

	@Test
	fun `all slugs are returned`() {
		every { slugServiceMock.getAllSlugs() } returns listOf(auntMilliesSlug(), nyTimesSlug())

		mockMvc
			.perform(get("/slugs"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$[0].id", equalTo("aunt-millies-and-co-inc")))
			.andExpect(jsonPath("$[1].id", equalTo("the-new-york-times")))
	}

	@Test
	fun `requested slug is returned`() {
		val slug = auntMilliesSlug()
		every { slugServiceMock.getSlug(any()) } returns slug

		mockMvc
			.perform(get("/slugs/${slug.id}"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.id", equalTo(slug.id)))
			.andExpect(jsonPath("$.descriptions", hasItem("Aunt Millie's & Co., Inc.")))
			.andExpect(jsonPath("$.url", equalTo("https://www.auntmillies.com/")))
	}

	@Test
	fun `slug is deleted and no content is returned`() {
		val stubSlugId = auntMilliesSlug().id
		every { slugServiceMock.deleteSlug(any()) } returns Unit

		mockMvc
			.perform(delete("/slugs/$stubSlugId"))
			.andExpect(status().isNoContent)
	}

	@Test
	fun `slug is created and returned`() {
		val slug = auntMilliesSlug()
		val body = """
			{
		    "url": "${slug.url}",
		    "description": "${slug.descriptions.joinToString("")}"
			}
		""".trimIndent()

		every { slugServiceMock.addSlug(any(), any()) } returns slug

		mockMvc
			.perform(
				post("/slugs").header(CONTENT_TYPE, APPLICATION_JSON).content(body)
			)
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.url", equalTo(slug.url)))
	}

	@Test
	fun `slug is updated and returned`() {
		val slug = auntMilliesSlug()
		val body = """
			{
		    "url": "${slug.url}"
			}
		""".trimIndent()

		every { slugServiceMock.updateSlug(any(), any()) } returns slug

		mockMvc
			.perform(
				post("/slugs/${slug.id}").header(CONTENT_TYPE, APPLICATION_JSON).content(body)
			)
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.url", equalTo(slug.url)))
	}
}