package feature.promotions.com.example.ffp.service

import feature.promotions.com.example.ffp.model.Promotion
import feature.promotions.com.example.ffp.repository.PromotionRepository
import org.springframework.stereotype.Service

@Service
class PromotionService(
    private val promotionRepository: PromotionRepository
) {
    fun getPromotionByCode(code: String): Promotion? {
        return promotionRepository.findByCode(code)
    }

    fun create(promotion: Promotion): Promotion {
        return promotionRepository.save(promotion)
    }
}
