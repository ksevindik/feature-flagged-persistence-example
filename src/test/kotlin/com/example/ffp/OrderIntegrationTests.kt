package com.example.ffp

import com.example.ffp.model.Order
import com.example.ffp.service.OrderService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal

class OrderIntegrationTests : BaseIntegrationTests() {

    @Autowired
    private lateinit var orderService: OrderService

    @Test
    fun `it should create and list orders`() {
        val order = Order()
        order.totalAmount = BigDecimal.TEN

        var orders = orderService.getOrders()
        Assertions.assertEquals(0,orders.size)

        orderService.create(order)

        flushAndClear()

        orders = orderService.getOrders()
        Assertions.assertEquals(1,orders.size)
    }
}