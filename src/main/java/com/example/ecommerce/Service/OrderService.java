package com.example.ecommerce.Service;

import com.example.ecommerce.Advices.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.example.ecommerce.DTO.*;
import com.example.ecommerce.Entity.Order;
import com.example.ecommerce.Entity.OrderItem;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.OrderRepository;
import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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

    @Transactional
    public OrderResponseDto placeOrder(OrderRequestDto dto) {

        // 1️⃣ Fetch user
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 2️⃣ Create order
        Order order = new Order();
        order.setUser(user);
        order.setStatus("CREATED");

        List<OrderItem> items = new ArrayList<>();

        // 3️⃣ Create order items
        for (OrderItemDto itemDto : dto.getItems()) {

            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            if (product.getQuantity() < itemDto.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product " + product.getName());
            }

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setPriceAtPurchase(product.getPrice());

            items.add(item);

            // reduce stock
            product.setQuantity(product.getQuantity() - itemDto.getQuantity());
        }

        // 4️⃣ Attach items ONCE
        order.setItems(items);

        // 5️⃣ Save ONCE (cascade saves order_items)
        Order savedOrder = orderRepository.save(order);

        // 6️⃣ Map to response DTO
        return mapToOrderResponse(savedOrder);
    }

    private OrderResponseDto mapToOrderResponse(Order order) {

        List<OrderItemResponseDto> itemDtos = order.getItems().stream()
                .map(item -> new OrderItemResponseDto(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getPriceAtPurchase(),
                        item.getQuantity()
                ))
                .toList();

        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(order.getId());
        response.setStatus(order.getStatus());
        response.setItems(itemDtos);

        return response;
    }


}
