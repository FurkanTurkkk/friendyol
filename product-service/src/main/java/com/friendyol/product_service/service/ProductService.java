package com.friendyol.product_service.service;

import com.friendyol.product_service.converter.DtoConverter;
import com.friendyol.product_service.model.Product;
import com.friendyol.product_service.repository.ProductRepository;
import com.friendyol.product_service.request.RequestForCreateProduct;
import org.example.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final DtoConverter converter;

    public ProductService(ProductRepository productRepository, DtoConverter converter) {
        this.productRepository = productRepository;
        this.converter = converter;
    }

    public ProductDto createProduct(RequestForCreateProduct request){
        Product product=new Product(request.getName(),
                request.getCategoryId(),
                request.getSupplierId(),
                request.getColor(),
                request.getDescription(),
                request.getPrice());
        saveProduct(product);
        return converter.convert(product);
    }

    public ProductDto findProductByProductId(Long id){
        Product product=findProductById(id);
        return converter.convert(product);
    }

    public List<ProductDto> findProductListByCategoryId(String categoryId){
        List<Product> productList=findAllProduct(categoryId);
        System.out.println(productList);
        return productList.stream().map(converter::convert).toList();
    }

    public void deleteAllProduct(){
        productRepository.deleteAll();
    }

    private List<Product> findAllProduct(String categoryId){
        return productRepository.findByCategoryId(categoryId);
    }

    private Product findProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Ürün bulunamadı"));
    }

    private void saveProduct(Product product){
        if (productRepository.findByNameAndColor(product.getName(),product.getColor()).isPresent()){
            throw new IllegalArgumentException("Ürün mevcut güncellemek için güncelleme sayfasına gidin");
        }
        productRepository.save(product);
    }

}
