package com.example.controller;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // список всех товаров
    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // товар по id
    @GetMapping("/{id}")
    public Optional<Product> getById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    // товар(ы) по названию: /products/search?name=Phone
    @GetMapping("/search")
    public List<Product> getByName(@RequestParam String name) {
        return productRepository.findByName(name);
        // если в репозитории используется findByNameContainingIgnoreCase:
        // return productRepository.findByNameContainingIgnoreCase(name);
    }

    // добавить товар
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // изменить товар по id (все поля заменяются присланными)
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product updated) {
        updated.setId(id);
        return productRepository.save(updated);
    }

    // удалить товар по id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}