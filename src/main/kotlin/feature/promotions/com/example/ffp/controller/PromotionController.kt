package feature.promotions.com.example.ffp.controller

import feature.promotions.com.example.ffp.model.Promotion
import feature.promotions.com.example.ffp.service.PromotionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/promotions")
class PromotionController(
    private val promotionService: PromotionService
) {
    @GetMapping
    fun getPromotionByCode(@RequestParam code: String): Promotion? {
        return promotionService.getPromotionByCode(code)
    }

    @PostMapping
    fun create(@RequestBody promotion: Promotion): Promotion {
        return promotionService.create(promotion)
    }
}

