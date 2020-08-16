package com.asurint.slug.api

import com.asurint.slug.domain.Slug
import com.asurint.slug.domain.SlugDescription
import com.asurint.slug.service.SlugService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItem
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders.*
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(SlugController::class)
class MockedSlugControllerTests {
	@Autowired
	private lateinit var mockMvc: MockMvc

	@MockkBean
	private lateinit var slugServiceMock: SlugService

	@Test
	fun `all slugs are returned`() {
		every { slugServiceMock.getAllSlugs() } returns listOf(
			Slug().apply {
				id = "aunt-millies-and-co-inc"
				url = "https://www.auntmillies.com/"
				descriptions = mutableListOf(
					SlugDescription().apply {
						id = 1
						slugId = "aunt-millies-and-co-inc"
						description = "Aunt Millie's & Co., Inc."
					}
				)
			},
			Slug().apply {
				id = "the-new-york-times"
				url = "https://www.nytimes.com/"
				descriptions = mutableListOf(
					SlugDescription().apply {
						id = 1
						slugId = "the-new-york-times"
						description = "The New York Times"
					}
				)
			}
		)

		mockMvc
			.perform(get("/slugs"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$[0].id", equalTo("aunt-millies-and-co-inc")))
			.andExpect(jsonPath("$[1].id", equalTo("the-new-york-times")))
	}

	@Test
	fun `requested slug is returned`() {
		val stubSlugId = "aunt-millies-and-co-inc"
		every { slugServiceMock.getSlug(any()) } returns Slug().apply {
			id = stubSlugId
			url = "https://www.auntmillies.com/"
			descriptions = mutableListOf(
				SlugDescription().apply {
					id = 1
					slugId = stubSlugId
					description = "Aunt Millie's & Co., Inc."
				}
			)
		}

		mockMvc
			.perform(get("/slugs/$stubSlugId"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.id", equalTo(stubSlugId)))
			.andExpect(jsonPath("$.descriptions", hasItem("Aunt Millie's & Co., Inc.")))
			.andExpect(jsonPath("$.url", equalTo("https://www.auntmillies.com/")))
	}

	@Test
	fun `slug is deleted and no content is returned`() {
		val stubSlugId = "aunt-millies-and-co-inc"
		every { slugServiceMock.deleteSlug(any()) } returns Unit

		mockMvc
			.perform(delete("/slugs/$stubSlugId"))
			.andExpect(status().isNoContent)
	}

	@Test
	fun `slug is created and returned`() {
		val slugId = "aunt-millies-and-co-inc"
		val url = "https://www.auntmillies.com/"
		val desc = "Aunt Millie's & Co., Inc."
		val body = """
			{
		    "url": "$url",
		    "description": "$desc"
			}
		""".trimIndent()

		every { slugServiceMock.addSlug(any(), any()) } returns Slug().apply {
			id = slugId
			this.url = url
			descriptions = mutableListOf(
				SlugDescription().apply {
					id = 1
					this.slugId = slugId
					description = desc
				}
			)
		}

		mockMvc
			.perform(
				post("/slugs").header(CONTENT_TYPE, APPLICATION_JSON).content(body)
			)
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.url", equalTo(url)))
	}

	@Test
	fun `slug is updated and returned`() {
		val slugId = "aunt-millies-and-co-inc"
		val url = "https://www.auntmillies.com/"
		val desc = "Aunt Millie's & Co., Inc."
		val body = """
			{
		    "url": "$url"
			}
		""".trimIndent()

		every { slugServiceMock.updateSlug(any(), any()) } returns Slug().apply {
			id = slugId
			this.url = url
			descriptions = mutableListOf(
				SlugDescription().apply {
					id = 1
					this.slugId = slugId
					description = desc
				}
			)
		}

		mockMvc
			.perform(
				post("/slugs/$slugId").header(CONTENT_TYPE, APPLICATION_JSON).content(body)
			)
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.url", equalTo(url)))
	}
}