package com.asurint.slug.processors

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DashesProcessingTests {
	@Test
	fun `consecutive dashes are replaced with a single dash globally`() {
		val stub = "A ---- B --- C -- D - E"
		val expected = "A - B - C - D - E"
		val result = ConsecutiveDashesProcessor().process(stub)
		assertThat(result).isEqualTo(expected)
	}

	@Test
	fun `dashes at the beginning are stripped`() {
		val stub = "--ABC"
		val expected = "ABC"
		val result = BeginningDashesProcessor().process(stub)
		assertThat(result).isEqualTo(expected)
	}

	@Test
	fun `dashes at the end are stripped`() {
		val stub = "ABC---"
		val expected = "ABC"
		val result = EndingDashesProcessor().process(stub)
		assertThat(result).isEqualTo(expected)
	}
}