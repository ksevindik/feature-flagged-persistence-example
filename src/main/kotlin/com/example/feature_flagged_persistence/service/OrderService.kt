package com.example.feature_flagged_persistence.service

import com.example.feature_flagged_persistence.model.Order
import com.example.feature_flagged_persistence.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService {
    @Autowired
    private lateinit var orderRepository: OrderRepository

    fun create(order: Order) {
        orderRepository.save(order)
    }

    fun getOrders() : List<Order> {
        return orderRepository.findAll()
    }
}