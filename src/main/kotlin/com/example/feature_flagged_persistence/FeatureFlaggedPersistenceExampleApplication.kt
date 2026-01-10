package com.example.feature_flagged_persistence

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FeatureFlaggedPersistenceExampleApplication

fun main(args: Array<String>) {
	runApplication<FeatureFlaggedPersistenceExampleApplication>(*args)
}
