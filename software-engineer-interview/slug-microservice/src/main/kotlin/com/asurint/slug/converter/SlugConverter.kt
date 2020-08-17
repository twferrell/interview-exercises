package com.asurint.slug.converter

import com.asurint.slug.loggerFor
import org.springframework.stereotype.Service

/**
 * This service implements a processing pipeline by utilizing a collection
 * of pluggable processors.  The processors form a 'chain of responsibility'
 * pattern through the implementation of a 'process' method and are processed
 * in sequence based on their relative 'order' property.
 *
 * @property processors form a processing pipeline based on their order property
 */
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

	/**
	 * Converts [description] to a slug.
	 * @return the slug based on the results from the processing pipeline.
	 */
	fun convert(description: String) = processors.fold(description) { slug, slugProcessor ->
		slugProcessor.process(slug)
	}
}

interface ISlugProcessor {
	val order: Int
	fun process(slug: String): String
}