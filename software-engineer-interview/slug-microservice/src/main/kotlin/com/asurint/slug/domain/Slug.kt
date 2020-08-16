package com.asurint.slug.domain

import javax.persistence.*

@Entity
@Table(name = "slugs", schema = "site_data")
class Slug {
	@Id
	@Column(name = "id")
	lateinit var id: String

	@Column(name = "url")
	lateinit var url: String

	@JoinColumn(name = "slug_id")
	@OneToMany(
		cascade = [CascadeType.ALL],
		orphanRemoval = true
	)
	lateinit var descriptions: MutableList<SlugDescription>
}