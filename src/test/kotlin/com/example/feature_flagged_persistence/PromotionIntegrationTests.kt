package com.example.feature_flagged_persistence

import feature.promotions.model.Promotion
import feature.promotions.service.PromotionService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestPropertySource
import java.math.BigDecimal

@TestPropertySource(properties = ["feature.promotions.enabled=true"])
class PromotionIntegrationTests : BaseIntegrationTests() {
    @Autowired
    private lateinit var promotionService: PromotionService

    @Test
    fun `it should create and filter promotion by code`() {
        val promotion = Promotion()
        promotion.code = "ABC"
        promotion.discountPercentage = BigDecimal.TEN

        var promotionFound = promotionService.getPromotion("ABC")
        Assertions.assertNull(promotionFound)

        promotionService.create(promotion)

        flushAndClear()

        promotionFound = promotionService.getPromotion("ABC")
        Assertions.assertNotNull(promotionFound)
    }
}