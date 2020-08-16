package com.asurint.slug.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.*

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class SlugControllerTests {

	@LocalServerPort
	private val port = 0

	@Autowired
	private lateinit var restTemplate: TestRestTemplate

	@Test
	fun `all persisted slugs are returned`() {
		val slugList = arrayOf(
			SlugDto("aunt-millies-and-co-inc", "https://www.auntmillies.com/", listOf("Aunt Millie's & Co., Inc.")),
			SlugDto("the-new-york-times", "https://www.nytimes.com/", listOf("The New York Times"))
		)

		assertThat(
			restTemplate.getForObject("$SERVER:$port/api/slugs", Array<SlugDto>::class.java)
		).isEqualTo(slugList)
	}

	@Test
	fun `persisted slug is returned`() {
		val slugId = "aunt-millies-and-co-inc"
		val slugDto = SlugDto(slugId, "https://www.auntmillies.com/", listOf("Aunt Millie's & Co., Inc."))

		assertThat(
			restTemplate.getForObject("$SERVER:$port/api/slugs/$slugId", SlugDto::class.java)
		).isEqualTo(slugDto)
	}

	@Test
	fun `slug is deleted successfully`() {
		val slugId = "aunt-millies-and-co-inc"

		// Expecting two records in seeded DB before deletion
		assertThat(
			restTemplate.getForObject("$SERVER:$port/api/slugs", Array<SlugDto>::class.java).size
		).isEqualTo(2)

		restTemplate.delete("$SERVER:$port/api/slugs/$slugId")

		// Expecting 1 record in seeded DB after deletion
		assertThat(
			restTemplate.getForObject("$SERVER:$port/api/slugs", Array<SlugDto>::class.java).size
		).isEqualTo(1)
	}

	@Test
	fun `slug is created successfully`() {
		val description = "Google Dot Com"
		val url = "https://www.google.com/"
		val expectedId = "google-dot-com"
		val body = SlugCreateRequestDto(url, description)
		val slugDto = SlugDto(expectedId, url, listOf(description))

		assertThat(
			restTemplate.postForObject("$SERVER:$port/api/slugs", body, SlugDto::class.java)
		).isEqualTo(slugDto)
	}

	@Test
	fun `slug is updated successfully`() {
		val slugId = "aunt-millies-and-co-inc"
		val newUrl = "https://www.somewhere-else.com/"
		val body = SlugUpdateRequestDto(newUrl)
		val slugDto = SlugDto(slugId, newUrl, listOf("Aunt Millie's & Co., Inc."))

		assertThat(
			restTemplate.postForObject("$SERVER:$port/api/slugs/$slugId", body, SlugDto::class.java)
		).isEqualTo(slugDto)
	}

	companion object {
		private const val SERVER = "http://localhost"
	}
}