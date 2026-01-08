package com.example.ecommerce.Service;

import com.example.ecommerce.Advices.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Repository.ProductRepository;
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
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<ProductDTO> createProduct(ProductDTO dto) {
        Product product = modelMapper.map(dto, Product.class);
        Product saved = productRepository.save(product);
        return ResponseEntity.ok(modelMapper.map(saved, ProductDTO.class));
    }

    public ResponseEntity<ProductDTO> findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null)
            throw new ResourceNotFoundException("resource with id " + id + " does not exist");
        return ResponseEntity.ok(modelMapper.map(product, ProductDTO.class));
    }

    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        List<ProductDTO> productDTOList = productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        return ResponseEntity.ok(productDTOList);
    }

    public ResponseEntity<Boolean> deleteById(Long id) {
        boolean isExist = productRepository.existsById(id);
        if (!isExist)
            throw new ResourceNotFoundException("resource with id " + id + " does not exist");

        productRepository.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    public ResponseEntity<ProductDTO> updateProductById(Long id, ProductDTO productDTO) {
        boolean isExist = productRepository.existsById(id);
        if (!isExist)
            throw new ResourceNotFoundException("resource with id " + id + " does not exist");

        Product product = modelMapper.map(productDTO, Product.class);
        product.setId(id); // important
        Product saved = productRepository.save(product);
        return ResponseEntity.ok(modelMapper.map(saved, ProductDTO.class));
    }

    public ResponseEntity<ProductDTO> partiallyUpdateProductById(Long id, Map<String, Object> map) {
        boolean isExist = productRepository.existsById(id);
        if (!isExist)
            throw new ResourceNotFoundException("resource with id " + id + " does not exist");

        Product product = productRepository.findById(id).orElseThrow();

        map.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Product.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, product, value);
            }
        });

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(modelMapper.map(savedProduct, ProductDTO.class));
    }
}
