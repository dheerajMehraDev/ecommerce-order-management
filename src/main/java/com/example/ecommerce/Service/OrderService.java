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
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // READ by id
    public ResponseEntity<OrderDto> findById(Long id) {
        log.info("Fetching order by id={}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Order not found with id={}", id);
                    return new ResourceNotFoundException("Order not found with id " + id);
                });

        log.info("Order found with id={}", id);
        return ResponseEntity.ok(modelMapper.map(order, OrderDto.class));
    }

    // READ all
    public ResponseEntity<List<OrderDto>> findAll() {
        log.info("Fetching all orders");

        List<OrderDto> orders = orderRepository.findAll()
                .stream()
                .map(o -> modelMapper.map(o, OrderDto.class))
                .toList();

        log.info("Total orders fetched={}", orders.size());
        return ResponseEntity.ok(orders);
    }

    // UPDATE
    public ResponseEntity<OrderDto> updateOrder(Long id, OrderDto dto) {
        log.info("Updating order id={} with status={}", id, dto.getStatus());

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Order not found for update, id={}", id);
                    return new ResourceNotFoundException("Order not found with id " + id);
                });

        order.setStatus(dto.getStatus());
        Order saved = orderRepository.save(order);

        log.info("Order updated successfully, id={}", id);
        return ResponseEntity.ok(modelMapper.map(saved, OrderDto.class));
    }

    // PARTIAL UPDATE
    public ResponseEntity<OrderDto> partialUpdate(Long id, Map<String, Object> map) {
        log.info("Partial update for order id={}, fields={}", id, map.keySet());

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Order not found for partial update, id={}", id);
                    return new ResourceNotFoundException("Order not found with id " + id);
                });

        map.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Order.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, order, value);
            }
        });

        Order saved = orderRepository.save(order);
        log.info("Partial update successful for order id={}", id);

        return ResponseEntity.ok(modelMapper.map(saved, OrderDto.class));
    }

    // DELETE
    public ResponseEntity<Boolean> deleteById(Long id) {
        log.info("Deleting order id={}", id);

        if (!orderRepository.existsById(id)) {
            log.warn("Order not found for delete, id={}", id);
            throw new ResourceNotFoundException("Order not found with id " + id);
        }

        orderRepository.deleteById(id);
        log.info("Order deleted successfully, id={}", id);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    // PLACE ORDER
    @Transactional
    public OrderResponseDto placeOrder(OrderRequestDto dto) {

        log.info("Placing order for userId={}", dto.getUserId());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> {
                    log.warn("User not found userId={}", dto.getUserId());
                    return new ResourceNotFoundException("User not found");
                });

        Order order = new Order();
        order.setUser(user);
        order.setStatus("CREATED");

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemDto itemDto : dto.getItems()) {

            log.info("Processing productId={} quantity={}",
                    itemDto.getProductId(), itemDto.getQuantity());

            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> {
                        log.warn("Product not found productId={}", itemDto.getProductId());
                        return new ResourceNotFoundException("Product not found");
                    });

            if (product.getQuantity() < itemDto.getQuantity()) {
                log.error("Insufficient stock for productId={}, available={}, requested={}",
                        product.getId(), product.getQuantity(), itemDto.getQuantity());
                throw new RuntimeException("Insufficient stock for product " + product.getName());
            }

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setPriceAtPurchase(product.getPrice());

            items.add(item);

            product.setQuantity(product.getQuantity() - itemDto.getQuantity());
        }

        order.setItems(items);
        Order savedOrder = orderRepository.save(order);

        log.info("Order placed successfully, orderId={}", savedOrder.getId());

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
