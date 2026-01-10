package com.example.feature_flagged_persistence.config

import com.example.feature_flagged_persistence.FeatureFlaggedPersistenceExampleApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.persistence.autoconfigure.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ConditionalOnProperty(
    value = ["feature.promotions.enabled"],
    havingValue = "true",
    matchIfMissing = false)
@Configuration
@EntityScan(basePackages = ["feature.promotions"], basePackageClasses = [FeatureFlaggedPersistenceExampleApplication::class])
@EnableJpaRepositories(basePackages = ["feature.promotions"], basePackageClasses = [FeatureFlaggedPersistenceExampleApplication::class])
@ComponentScan(basePackages = ["feature.promotions"])
class FeaturePromotionsJpaConfig {
}