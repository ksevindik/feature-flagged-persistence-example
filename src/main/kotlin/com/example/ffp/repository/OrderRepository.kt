package com.example.ffp.repository

import com.example.ffp.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order,Long> {
}