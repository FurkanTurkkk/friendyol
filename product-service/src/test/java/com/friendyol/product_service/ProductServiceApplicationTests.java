package com.friendyol.product_service;

import com.friendyol.product_service.converter.DtoConverter;
import com.friendyol.product_service.model.Product;
import com.friendyol.product_service.repository.ProductRepository;
import com.friendyol.product_service.request.RequestForCreateProduct;
import com.friendyol.product_service.service.ProductService;
import com.friendyol.product_service.util.FeignClientService;
import org.example.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceApplicationTests {

	private ProductService productService;

	private ProductRepository productRepository;
	private DtoConverter dtoConverter;
	private FeignClientService feignClientService;

	@BeforeEach
	void setUp(){

		productRepository= Mockito.mock(ProductRepository.class);
		dtoConverter=Mockito.mock(DtoConverter.class);
		feignClientService= Mockito.mock(FeignClientService.class);

		productService=new ProductService(productRepository,dtoConverter,feignClientService);

	}

	@DisplayName("shouldCreateProductAndReturnProductDto_whenCategoryIdExistAndProductCouldNotFoundWithColorAndNameInProductRepository")
	@Test
	void shouldCreateProductAndReturnProductDto_whenCategoryIdExistAndProductCouldNotFoundWithColorAndNameInProductRepository(){
		String categoryId="categoryId";
		String productColor="blue";
		String productName="Iphone15";
		BigDecimal productPrice=BigDecimal.valueOf(1500);
		Long stock=100L;
		String categoryName="categoryName";
		Long supplierId=1L;
		RequestForCreateProduct request=new RequestForCreateProduct(
				productName,
				categoryId,
				supplierId,
				productColor,
				"Çok iyi",
				productPrice
		);
		Product product=new Product(
				request.getName(),
				request.getCategoryId(),
				request.getSupplierId(),
				request.getColor(),
				request.getDescription(),
				request.getPrice()
				);

		ProductDto productDto=new ProductDto(
				product.getName(),
				stock,
				product.getPrice(),
				categoryName,
				product.getColor(),
				product.getDescription()
		);

		Mockito.when(productRepository.findByNameAndColor(productName,productColor)).thenReturn(Optional.empty());
		Mockito.when(productRepository.save(product)).thenReturn(product);
		Mockito.when(feignClientService.getCategoryNameByCategoryId(product)).thenReturn(categoryName);
		Mockito.when(dtoConverter.convert(product)).thenReturn(productDto);

		ProductDto result=productService.createProduct(request);

		assertEquals(productDto,result);

		Mockito.verify(productRepository).findByNameAndColor(product.getName(),productColor);
		Mockito.verify(productRepository).save(product);
		Mockito.verify(feignClientService).getCategoryNameByCategoryId(product);
		Mockito.verify(dtoConverter).convert(product);


	}

}
