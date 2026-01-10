package feature.promotions.repository

import feature.promotions.model.Promotion
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PromotionRepository : JpaRepository<Promotion, Long> {
    fun findByCode(code:String) : Optional<Promotion>
}