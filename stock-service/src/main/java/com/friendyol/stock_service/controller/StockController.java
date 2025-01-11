package com.friendyol.stock_service.controller;

import com.friendyol.stock_service.service.StockService;
import org.example.StockDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/product/{productId}/quantity")
    public ResponseEntity<StockDto> createStock(@PathVariable("productId")Long productId,@RequestBody Long quantity){
        return ResponseEntity.ok(stockService.createStock(productId,quantity));
    }

    @PutMapping("/update-stock/{productId}")
    public void updateStock(@PathVariable("productId")Long productId,
                            @RequestBody Long quantity){
        stockService.updateStockByProductId(productId,quantity);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<StockDto> findStockInformationByProductId(@PathVariable("productId")Long productId){
        return ResponseEntity.ok(stockService.findStockInformationByProductId(productId));
    }

    @DeleteMapping("/delete-product-by-productId/{productId}")
    public ResponseEntity<String> deleteStockInformationByProductId(@PathVariable("productId")Long productId){
        return ResponseEntity.ok(stockService.deleteStockInformationByProductId(productId));
    }

}
