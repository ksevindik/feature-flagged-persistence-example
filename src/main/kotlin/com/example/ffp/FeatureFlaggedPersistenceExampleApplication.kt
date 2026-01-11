package com.example.ffp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FeatureFlaggedPersistenceExampleApplication

fun main(args: Array<String>) {
	runApplication<FeatureFlaggedPersistenceExampleApplication>(*args)
}
