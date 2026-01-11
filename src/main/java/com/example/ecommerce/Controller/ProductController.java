package com.example.ecommerce.Controller;

import com.example.ecommerce.Advices.ApiResponse.ApiResponse;
import com.example.ecommerce.DTO.FakeApiDtos.FakeProductDto;
import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // create product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO dto) {
        return productService.createProduct(dto);
    }

    // get product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    // get all products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return productService.findAllProducts();
    }

    // delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable Long id) {
        return productService.deleteById(id);
    }

    // update product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductById(
            @PathVariable Long id,
            @RequestBody @Valid ProductDTO productDTO) throws Exception {
        return productService.updateProductById(id, productDTO);
    }

    // partially update product
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> partiallyUpdateProductById(
            @PathVariable Long id,
            @RequestBody Map<String, Object> map) throws Exception {
        return productService.partiallyUpdateProductById(id, map);
    }

    @GetMapping("/fakestore")
    public ApiResponse<List<FakeProductDto>> getFakeProducts(){
        return productService.getFakeProducts();
    }
}
