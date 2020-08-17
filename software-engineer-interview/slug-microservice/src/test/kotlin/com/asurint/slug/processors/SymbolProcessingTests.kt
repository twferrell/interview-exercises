package com.asurint.slug.processors

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

/**
 * A set of unit tests on individual processors,
 * focused on processing the required rules for handling special character-symbols.
 */
class SymbolProcessingTests {
	@Test
	fun `at symbols are replaced globally`() {
		val stub = "A @ B @ C"
		val expected = "A at B at C"
		val result = AtSymbolProcessor().process(stub)
		assertThat(result).isEqualTo(expected)
	}

	@Test
	fun `ampersand symbols are replaced globally`() {
		val stub = "A & B & C"
		val expected = "A and B and C"
		val result = AmpersandSymbolProcessor().process(stub)
		assertThat(result).isEqualTo(expected)
	}

	@Test
	fun `percent symbols are replaced globally`() {
		val stub = "A % B % C"
		val expected = "A percent B percent C"
		val result = PercentSymbolProcessor().process(stub)
		assertThat(result).isEqualTo(expected)
	}

	@Test
	fun `spaces are replaced with dashes globally`() {
		val stub = "A B C"
		val expected = "A-B-C"
		val result = SpacesProcessor().process(stub)
		assertThat(result).isEqualTo(expected)
	}

	@Test
	fun `letters are lower-cased globally`() {
		val stub = "Abc-DEF-GhI"
		val expected = "abc-def-ghi"
		val result = CaseProcessor().process(stub)
		assertThat(result).isEqualTo(expected)
	}

	@Test
	fun `invalid symbols are removed globally`() {
		val stub = "a_()_^bc#{?.~de+|fg}=hi$"
		val expected = "abcdefghi"
		val result = InvalidSymbolProcessor().process(stub)
		assertThat(result).isEqualTo(expected)
	}
}