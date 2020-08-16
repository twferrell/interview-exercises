package com.asurint.slug.processors

import com.asurint.slug.converter.ISlugProcessor
import org.springframework.stereotype.Component

@Component
class CaseProcessor : ISlugProcessor {
	override val order: Int = 1000
	override fun process(slug: String) = slug.toLowerCase()
}