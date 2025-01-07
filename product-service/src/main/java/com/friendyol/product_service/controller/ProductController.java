package com.friendyol.product_service.controller;

import com.friendyol.product_service.request.RequestForCreateProduct;
import com.friendyol.product_service.service.ProductService;
import org.example.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody RequestForCreateProduct request){
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PutMapping("/update-category/old-category/{oldCategoryId}/new-category/{newCategoryId}")
    public ResponseEntity<List<ProductDto>> updateCategoryOfProductByCategoryId(@PathVariable("oldCategoryId")String oldCategoryId,
                                                                          @PathVariable("newCategoryId")String newCategoryId){
        return ResponseEntity.ok(productService.updateCategoryOfProductByCategoryId(oldCategoryId,newCategoryId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findProductByProductId(@PathVariable("productId")Long id){
        return ResponseEntity.ok(productService.findProductByProductId(id));
    }

    @GetMapping("/get-all-products/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> findProductListByCategoryId(@PathVariable("categoryId")String categoryId){
        return ResponseEntity.ok(productService.findProductListByCategoryId(categoryId));
    }

    @GetMapping("/product-price/{productId}")
    public ResponseEntity<BigDecimal> findProductPriceByProductId(@PathVariable("productId")Long productId){
        return ResponseEntity.ok(productService.findProductPriceByProductId(productId));
    }

    @GetMapping("product-name/{productId}")
    public ResponseEntity<String> findProductNameByProductId(@PathVariable("productId")Long id){
        return ResponseEntity.ok(productService.findProductNameByProductId(id));
    }

    @DeleteMapping("/delete-by-productId/{productId}")
    public ResponseEntity<String> deleteProductByProductId(@PathVariable("productId")Long productId){
        productService.deleteProductByProductId(productId);
        return ResponseEntity.ok("Ürün başarıyla silindi stok bilgisi kaldırıldı");
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllProduct(){
        productService.deleteAllProduct();
        return ResponseEntity.ok("Tüm ürünler kaldırıldı");
    }

}
