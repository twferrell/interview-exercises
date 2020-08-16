package com.asurint.slug.converter

import org.springframework.stereotype.Service

@Service
class SlugConverter(val processors: List<ISlugProcessor>) {
	fun convert(description: String) = processors
		.sortedBy(ISlugProcessor::order)
		.fold(description, ::process)

	private fun process(slug: String, slugProcessor: ISlugProcessor) = slugProcessor.process(slug)
}

interface ISlugProcessor {
	val order: Int
	fun process(slug: String): String
}