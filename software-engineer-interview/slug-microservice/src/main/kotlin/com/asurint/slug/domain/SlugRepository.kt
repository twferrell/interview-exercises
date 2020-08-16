package com.asurint.slug.domain

import org.springframework.data.jpa.repository.JpaRepository

interface SlugRepository : JpaRepository<Slug, String>