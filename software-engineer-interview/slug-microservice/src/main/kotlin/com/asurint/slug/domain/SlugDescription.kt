package com.asurint.slug.domain

import javax.persistence.*

@Entity
@Table(name = "slug_descriptions", schema = "site_data")
class SlugDescription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long = 0

	@Column(name = "slug_id")
	lateinit var slugId: String

	@Column(name = "description")
	lateinit var description: String
}