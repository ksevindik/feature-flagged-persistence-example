package com.example.ffp

import feature.promotions.com.example.ffp.model.Promotion
import feature.promotions.com.example.ffp.service.PromotionService
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

        var promotionFound = promotionService.getPromotionByCode("ABC")
        Assertions.assertNull(promotionFound)

        promotionService.create(promotion)

        flushAndClear()

        promotionFound = promotionService.getPromotionByCode("ABC")
        Assertions.assertNotNull(promotionFound)
    }
}