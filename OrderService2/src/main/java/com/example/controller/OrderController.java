package com.example.controller;

import com.example.model.Order;
import com.example.model.OrderItem;
import com.example.repository.OrderRepository;
import com.example.repository.OrderItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderController(OrderRepository orderRepository,
                           OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    // Получить все заказы
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Получить заказ по ID
    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id);
    }

    // Создать заказ (передаём JSON с заказом и товарами)
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        // сначала сохраняем сам заказ
        Order savedOrder = orderRepository.save(order);

        // сохраняем товары в заказе
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                item.setOrder(savedOrder); // привязываем к заказу
                orderItemRepository.save(item);
            }
        }

        return savedOrder;
    }

    // Удалить заказ по ID
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}
