package com.friendyol.order_service.util;

import com.friendyol.order_service.feingclient.CartFeignClient;
import com.friendyol.order_service.feingclient.ProductFeignClient;
import com.friendyol.order_service.feingclient.StockFeignClient;
import org.example.CartDto;
import org.springframework.stereotype.Component;

@Component
public class FeignClientService {

    private final CartFeignClient cartFeignClient;
    private final ProductFeignClient productFeignClient;
    private final StockFeignClient stockFeignClient;

    public FeignClientService(CartFeignClient cartFeignClient,
                              ProductFeignClient productFeignClient,
                              StockFeignClient stockFeignClient) {
        this.cartFeignClient = cartFeignClient;
        this.productFeignClient = productFeignClient;
        this.stockFeignClient = stockFeignClient;
    }

    public String findProductNameByProductId(Long id){
        return productFeignClient.findProductNameByProductId(id).getBody();
    }

    public Long findProductIdByProductName(String productName){
        return productFeignClient.findProductIdByProductName(productName).getBody();
    }

    public void deleteCart(Long cartId){
        cartFeignClient.deleteCartByCartId(cartId);
    }

    public CartDto findCartByUserId(String userId){
        return cartFeignClient.findCartByUserId(userId).getBody();
    }

    public void updateStock(Long productId,Long quantity){
        stockFeignClient.updateStock(productId,quantity);
    }


}
