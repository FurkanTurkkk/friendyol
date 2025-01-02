package com.friendyol.product_service.controller;

import com.friendyol.product_service.request.RequestForCreateProduct;
import com.friendyol.product_service.service.ProductService;
import org.example.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody RequestForCreateProduct request){
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findProductByProductId(@PathVariable("productId")Long id){
        return ResponseEntity.ok(productService.findProductByProductId(id));
    }

    @GetMapping("/products/{categoryId}")
    public ResponseEntity<List<ProductDto>> findProductListByCategoryId(@PathVariable("categoryId")String categoryId){
        return ResponseEntity.ok(productService.findProductListByCategoryId(categoryId));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllProduct(){
        productService.deleteAllProduct();
        return ResponseEntity.ok("Tüm ürünler kaldırıldı");
    }

}
