package com.asurint.slug.domain

import javax.persistence.*

/**
 * Multiple descriptions may result in the same slug, therefore
 * the different variations used are persisted in a foreign table.
 */
@Entity
@Table(name = "slug_descriptions", schema = "site_data")
data class SlugDescription(
	@Column(name = "slug_id")
	val slugId: String,

	@Column(name = "description")
	val description: String
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long = 0
}
