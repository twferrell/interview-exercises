package com.asurint.slug.processors

import com.asurint.slug.converter.ISlugProcessor
import org.springframework.stereotype.Component

@Component
class AtSymbolProcessor : ISlugProcessor {
	override val order: Int = 1
	override fun process(slug: String) = slug.replace("@", "at")
}