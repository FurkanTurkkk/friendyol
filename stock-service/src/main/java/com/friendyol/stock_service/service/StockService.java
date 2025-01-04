package com.friendyol.stock_service.service;

import com.friendyol.stock_service.converter.DtoConverter;
import com.friendyol.stock_service.model.Stock;
import com.friendyol.stock_service.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.example.StockDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final DtoConverter converter;

    public StockService(StockRepository stockRepository, DtoConverter converter) {
        this.stockRepository = stockRepository;
        this.converter = converter;
    }

    @Transactional
    public StockDto createStock(Long productId,Long quantity){
        Stock stock=new Stock(productId,quantity,LocalDate.now());
        Stock registeredStock=saveStock(stock);
        return converter.convertToStockDto(registeredStock);
    }

    public StockDto findStockInformationByProductId(Long productId){
        return converter.convertToStockDto(findByProductId(productId));
    }

    public String deleteStockInformationByProductId(Long productId){
        Stock stock=findByProductId(productId);
        stockRepository.delete(stock);
        return "Stok bilgisi başarıyla silindi";
    }

    private Stock findByProductId(Long productId){
        return stockRepository.findByProductId(productId)
                .orElseThrow(()->new RuntimeException("Bu product id ye sahip ürün bulunamadı product id : "+productId));
    }

    private Stock saveStock(Stock stock){
        Optional<Stock> registeredStock=stockRepository.findByProductId(stock.getProductId());
        if(registeredStock.isPresent()){
            Stock existingStock=registeredStock.get();
            existingStock.increaseQuantity(stock.getQuantity());
            return stockRepository.save(existingStock);
        }
        return stockRepository.save(stock);
    }

}
