package feature.promotions.com.example.ffp.repository

import feature.promotions.com.example.ffp.model.Promotion
import org.springframework.data.jpa.repository.JpaRepository

interface PromotionRepository : JpaRepository<Promotion, Long> {
    fun findByCode(code: String): Promotion?
}
