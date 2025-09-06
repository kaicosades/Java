package com.example.controller;

import com.example.model.OrderItem;
import com.example.repository.OrderItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemRepository orderItemRepository;

    public OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // Получить все позиции заказов
    @GetMapping
    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    // Получить позицию по ID
    @GetMapping("/{id}")
    public Optional<OrderItem> getById(@PathVariable Long id) {
        return orderItemRepository.findById(id);
    }

    // Создать позицию заказа
    @PostMapping
    public OrderItem create(@RequestBody OrderItem item) {
        return orderItemRepository.save(item);
    }

    // Обновить позицию по ID
    @PutMapping("/{id}")
    public OrderItem update(@PathVariable Long id, @RequestBody OrderItem updated) {
        updated.setId(id);
        return orderItemRepository.save(updated);
    }

    // Удалить позицию по ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderItemRepository.deleteById(id);
    }
}
