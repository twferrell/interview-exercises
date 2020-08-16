package com.asurint.slug.processors

import com.asurint.slug.converter.ISlugProcessor
import org.springframework.stereotype.Component

@Component
class InvalidSymbolProcessor : ISlugProcessor {
	private val regEx = PATTERN.toRegex()
	override val order: Int = 10
	override fun process(slug: String) = regEx.replace(slug, "")

	companion object {
		private const val PATTERN = """[^a-zA-Z\d-]"""
	}
}