package com.asurint.slug.domain

import javax.persistence.*

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
