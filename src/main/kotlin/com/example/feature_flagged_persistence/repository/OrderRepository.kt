package com.example.feature_flagged_persistence.repository

import com.example.feature_flagged_persistence.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order,Long> {
}