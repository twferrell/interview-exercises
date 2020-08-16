package com.asurint.slug.converter

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SlugConverterTests {
	@Autowired
	private lateinit var slugConverter: SlugConverter

	@ParameterizedTest
	@MethodSource("arguments")
	fun `descriptions are converted to expected slugs`(desc: String, expectedSlug: String) {
		assertThat(slugConverter.convert(desc)).isEqualTo(expectedSlug)
	}

	private companion object {
		@JvmStatic
		@Suppress("unused")
		private fun arguments() = arguments(
			of("Aunt Millie's & Co., Inc.", "aunt-millies-and-co-inc"),
			of("  Aunt Millie's & * .    Co., Inc. -- ", "aunt-millies-and-co-inc"),
			of("__ AUNT Millie's & Co.,, , *~^#() Inc. ", "aunt-millies-and-co-inc"),
			of("-_ -. ]_ Aunt Millie's & Co.,, , *~^ Inc. ).  - -  ", "aunt-millies-and-co-inc"),
			of("-_ -. ]_ Aunt Millie's & Co' .,, , *~^ Inc _ ++. ).  - -  ", "aunt-millies-and-co-inc")
		).get()
	}
}