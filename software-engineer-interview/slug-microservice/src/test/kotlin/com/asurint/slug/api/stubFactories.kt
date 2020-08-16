package com.asurint.slug.api

import com.asurint.slug.domain.Slug
import com.asurint.slug.domain.SlugDescription

fun auntMilliesSlug(): Slug {
  val slugId = "aunt-millies-and-co-inc"
  val slugUrl = "https://www.auntmillies.com/"
  val slugDescription = SlugDescription(slugId, "Aunt Millie's & Co., Inc.").apply { id = 1 }
  val descriptions = mutableSetOf(slugDescription)
  return Slug(slugId, slugUrl, descriptions)
}

fun nyTimesSlug(): Slug {
  val slugId = "the-new-york-times"
  val slugUrl = "https://www.nytimes.com/"
  val slugDescription = SlugDescription(slugId, "The New York Times").apply { id = 1 }
  val descriptions = mutableSetOf(slugDescription)
  return Slug(slugId, slugUrl, descriptions)
}