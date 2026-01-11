package com.example.ffp.controller

import com.example.ffp.model.Order
import com.example.ffp.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping
    fun create(@RequestBody order: Order): Order {
        orderService.create(order)
        return order
    }

    @GetMapping
    fun list(): List<Order> {
        return orderService.getOrders()
    }
}

