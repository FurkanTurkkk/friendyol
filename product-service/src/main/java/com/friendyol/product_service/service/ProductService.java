package com.friendyol.product_service.service;

import com.friendyol.product_service.converter.DtoConverter;
import com.friendyol.product_service.model.Product;
import com.friendyol.product_service.repository.ProductRepository;
import com.friendyol.product_service.request.RequestForCreateProduct;
import com.friendyol.product_service.util.FeignClientService;
import jakarta.transaction.Transactional;
import org.example.ProductDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final DtoConverter converter;
    private final FeignClientService feignClientService;

    public ProductService(ProductRepository productRepository, DtoConverter converter, FeignClientService feignClientService) {
        this.productRepository = productRepository;
        this.converter = converter;
        this.feignClientService = feignClientService;
    }

    public ProductDto createProduct(RequestForCreateProduct request){
        Product product=new Product(request.getName(),
                request.getCategoryId(),
                request.getSupplierId(),
                request.getColor(),
                request.getDescription(),
                request.getPrice());
        saveProduct(product);
        feignClientService.createStock(product.getId(),0L);
        return converter.convert(product);
    }

    public String findProductNameByProductId(Long productId){
        return findProductById(productId).getName();
    }

    public ProductDto findProductByProductId(Long id){
        Product product=findProductById(id);
        return converter.convert(product);
    }

    public List<ProductDto> findProductListByCategoryId(String categoryId){
        List<Product> productList= findAllProductByCategoryId(categoryId);
        return productList.stream().map(converter::convert).toList();
    }


    public List<ProductDto> updateCategoryOfProductByCategoryId(String oldCategoryId,String newCategoryId){
        List<Product> existProductList=findAllProductByCategoryId(oldCategoryId);
        List<Product> registeredProductList=existProductList.stream()
                .map(product -> product.updateCategoryIdOfProduct(newCategoryId)).toList();
        registeredProductList.stream().forEach(productRepository::save);
        return registeredProductList.stream().map(converter::convert).toList();
    }

    public BigDecimal findProductPriceByProductId(Long productId){
        Product product=findProductById(productId);
        return product.getPrice();
    }

    @Transactional
    public void deleteProductByProductId(Long productId){
        Product product=findProductById(productId);
        productRepository.delete(product);
        feignClientService.deleteStockInformationByProductId(productId);
    }

    public void deleteAllProduct(){
        productRepository.deleteAll();
    }

    private List<Product> findAllProductByCategoryId(String categoryId){
        return productRepository.findByCategoryId(categoryId);
    }

    private Product findProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Ürün bulunamadı"));
    }

    private void saveProduct(Product product){
        if(feignClientService.getCategoryNameByCategoryId(product).isEmpty()){
            throw new RuntimeException("Kategori bulunamadı");
        }
        if (productRepository.findByNameAndColor(product.getName(),product.getColor()).isPresent()){
            throw new IllegalArgumentException("Ürün mevcut güncellemek için güncelleme sayfasına gidin");
        }
        productRepository.save(product);
    }


}
