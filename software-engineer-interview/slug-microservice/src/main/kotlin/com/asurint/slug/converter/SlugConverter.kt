package com.asurint.slug.converter

import com.asurint.slug.loggerFor
import org.springframework.stereotype.Service

@Service
class SlugConverter(processors: List<ISlugProcessor>) {
	private val processors = processors.sortedBy(ISlugProcessor::order)
	private val logger = loggerFor(javaClass)

	init {
		if (logger.isDebugEnabled) {
			logger.debug("Processing Order:")
			this.processors.forEach(::println)
		}
	}

	fun convert(description: String) = processors.fold(description) { slug, slugProcessor ->
		slugProcessor.process(slug)
	}
}

interface ISlugProcessor {
	val order: Int
	fun process(slug: String): String
}