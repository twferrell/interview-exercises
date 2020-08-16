package com.asurint.slug.domain

import javax.persistence.*

@Entity
@Table(name = "slugs", schema = "site_data")
class Slug(
	@Id
	@Column(name = "id")
	val id: String,

	@Column(name = "url")
	var url: String,

	@JoinColumn(name = "slug_id")
	@OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
	val descriptions: MutableSet<SlugDescription>
) {
	fun addDescription(description: String) = descriptions.add(SlugDescription(id, description))
}