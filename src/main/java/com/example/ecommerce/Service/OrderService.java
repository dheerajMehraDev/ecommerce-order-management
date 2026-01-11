package com.example.ecommerce.Service;

import com.example.ecommerce.Advices.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.example.ecommerce.DTO.OrderDto;
import com.example.ecommerce.Entity.Order;
import com.example.ecommerce.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    // CREATE
    public ResponseEntity<OrderDto> createOrder(OrderDto dto) {
        Order order = modelMapper.map(dto, Order.class);
        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(modelMapper.map(saved, OrderDto.class));
    }

    // READ by id
    public ResponseEntity<OrderDto> findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        return ResponseEntity.ok(modelMapper.map(order, OrderDto.class));
    }

    // READ all
    public ResponseEntity<List<OrderDto>> findAll() {
        List<OrderDto> orders = orderRepository.findAll()
                .stream()
                .map(o -> modelMapper.map(o, OrderDto.class))
                .toList();
        return ResponseEntity.ok(orders);
    }

    // UPDATE (status only, logically)
    public ResponseEntity<OrderDto> updateOrder(Long id, OrderDto dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        order.setStatus(dto.getStatus());
        Order saved = orderRepository.save(order);

        return ResponseEntity.ok(modelMapper.map(saved, OrderDto.class));
    }

    // PARTIAL UPDATE
    public ResponseEntity<OrderDto> partialUpdate(Long id, Map<String, Object> map) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        map.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Order.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, order, value);
        });

        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(modelMapper.map(saved, OrderDto.class));
    }

    // DELETE (optional – allowed for now)
    public ResponseEntity<Boolean> deleteById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
