package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.OrderDto;
import com.example.ecommerce.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // CREATE
    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto dto) {
        return orderService.createOrder(dto);
    }

    // READ by id
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    // READ all
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        return orderService.findAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(
            @PathVariable Long id,
            @RequestBody @Valid OrderDto dto) {
        return orderService.updateOrder(id, dto);
    }

    // PARTIAL UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<OrderDto> partialUpdate(
            @PathVariable Long id,
            @RequestBody @Valid Map<String, Object> map) {
        return orderService.partialUpdate(id, map);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return orderService.deleteById(id);
    }
}
