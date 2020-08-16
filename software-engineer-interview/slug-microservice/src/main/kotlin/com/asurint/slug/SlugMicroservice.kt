package com.asurint.slug

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SlugMicroservice

fun main(args: Array<String>) {
	runApplication<SlugMicroservice>(*args)
}
