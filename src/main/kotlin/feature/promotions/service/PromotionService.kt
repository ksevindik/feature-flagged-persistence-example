package feature.promotions.service

import feature.promotions.model.Promotion
import feature.promotions.repository.PromotionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull


@Service
class PromotionService {
    @Autowired
    private lateinit var promotionRepository: PromotionRepository

    fun create(promotion: Promotion) {
        promotionRepository.save(promotion)
    }

    fun getPromotion(code:String) : Promotion? {
        return promotionRepository.findByCode(code).getOrNull()
    }
}