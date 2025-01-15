package com.friendyol.product_service;

import com.friendyol.product_service.converter.DtoConverter;
import com.friendyol.product_service.model.Product;
import com.friendyol.product_service.repository.ProductRepository;
import com.friendyol.product_service.request.RequestForCreateProduct;
import com.friendyol.product_service.service.ProductService;
import com.friendyol.product_service.util.FeignClientService;
import feign.FeignException;
import org.example.ProductDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceApplicationTests {

	private ProductService productService;

	private ProductRepository productRepository;
	private DtoConverter dtoConverter;
	private FeignClientService feignClientService;
	private Product mockProduct;

	@BeforeEach
	void setUp(){

		productRepository= Mockito.mock(ProductRepository.class);
		dtoConverter=Mockito.mock(DtoConverter.class);
		feignClientService= Mockito.mock(FeignClientService.class);
		mockProduct=Mockito.mock(Product.class);
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

		Mockito.verify(feignClientService).getCategoryNameByCategoryId(product);
		Mockito.verify(productRepository).findByNameAndColor(product.getName(),productColor);
		Mockito.verify(productRepository).save(product);
		Mockito.verify(dtoConverter).convert(product);


	}

	@DisplayName("shouldThrowProductExistException_whenCategoryIdExistAndProductFoundWithColorAndNameInProductRepository")
	@Test
	void shouldThrowProductExistException_whenCategoryIdExistAndProductFoundWithColorAndNameInProductRepository(){
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


		Mockito.when(feignClientService.getCategoryNameByCategoryId(product)).thenReturn(categoryName);
		Mockito.when(productRepository.findByNameAndColor(productName,productColor)).thenReturn(Optional.of(product));


		assertThrows(IllegalArgumentException.class,
				()->productService.createProduct(request));

		Mockito.verify(feignClientService).getCategoryNameByCategoryId(product);
		Mockito.verify(productRepository).findByNameAndColor(product.getName(),productColor);

	}

	@DisplayName("shouldThrowRunTimeException_whenCategoryIdNotExistAndProductFoundOrCouldNotFoundWithColorAndName")
	@Test
	void shouldThrowRunTimeException_whenCategoryIdNotExistAndProductFoundOrCouldNotFoundWithColorAndName(){
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


		Mockito.when(feignClientService.getCategoryNameByCategoryId(product)).thenThrow(FeignException.FeignClientException.class);


		assertThrows(RuntimeException.class,
				()->productService.createProduct(request));

		Mockito.verify(feignClientService).getCategoryNameByCategoryId(product);
	}

	@DisplayName("shouldFindProductByProductId_WhenProductExistInProductRepositoryAndReturnProductName")
	@Test
	void shouldFindProductByProductId_WhenProductExistInProductRepositoryAndReturnProductName(){
		String categoryId="categoryId";
		String productColor="blue";
		String productName="Iphone15";
		BigDecimal productPrice=BigDecimal.valueOf(1500);
		Product product=new Product(
				productName,
				categoryId,
				1L,
				productColor,
				"Aşırı iyi",
				productPrice
		);

		Long productId=product.getId();

		Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));

		String result=productService.findProductNameByProductId(productId);

		assertEquals(productName,result);

		Mockito.verify(productRepository).findById(productId);

	}

	@DisplayName("shouldThrowRuntimeException_WhenProductNotExistInProductRepository")
	@Test
	void shouldThrowRuntimeException_WhenProductNotExistInProductRepository(){
		String categoryId="categoryId";
		String productColor="blue";
		String productName="Iphone15";
		BigDecimal productPrice=BigDecimal.valueOf(1500);
		Product product=new Product(
				productName,
				categoryId,
				1L,
				productColor,
				"Aşırı iyi",
				productPrice
		);

		Long productId=product.getId();

		Mockito.when(productRepository.findById(productId)).thenThrow(RuntimeException.class);

		assertThrows(RuntimeException.class,()->productService.findProductNameByProductId(productId));

		Mockito.verify(productRepository).findById(productId);

	}

	@DisplayName("shouldReturnProductDto_whenProductExistInProductRepositoryForProductId")
	@Test
	void shouldReturnProductDto_whenProductExistInProductRepositoryForProductId(){
		String productName="Samsung 23";
		String categoryId="categoryId";
		Long supplierId=1L;
		String productColor="red";
		String description="Very good";
		String categoryName="categoryName";
		BigDecimal price=BigDecimal.valueOf(1500);
		Product product=new Product(
				productName,
				categoryId,
				supplierId,
				productColor,
				description,
				price
		);
		Long productId=product.getId();
		ProductDto productDto=new ProductDto(
				product.getName(),
				100L,
				product.getPrice(),
				categoryName,
				product.getColor(),
				product.getDescription()
		);

		Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
		Mockito.when(dtoConverter.convert(product)).thenReturn(productDto);

		ProductDto result=productService.findProductByProductId(productId);

		assertEquals(productDto,result);

		Mockito.verify(productRepository).findById(productId);
		Mockito.verify(dtoConverter).convert(product);

	}

	@DisplayName("shouldThrowException_whenProductNotExistInProductRepositoryForProductId")
	@Test
	void shouldThrowException_whenProductNotExistInProductRepositoryForProductId(){
		String productName="Samsung 23";
		String categoryId="categoryId";
		Long supplierId=1L;
		String productColor="red";
		String description="Very good";
		BigDecimal price=BigDecimal.valueOf(1500);
		Product product=new Product(
				productName,
				categoryId,
				supplierId,
				productColor,
				description,
				price
		);
		Long productId=product.getId();

		Mockito.when(productRepository.findById(productId)).thenThrow(RuntimeException.class);

		assertThrows(RuntimeException.class,()->productService.findProductByProductId(productId));

		Mockito.verify(productRepository).findById(productId);

	}

	@DisplayName("shouldReturnListOfProductDto_whenProductsExistWithCategoryIdAndConvertFromProductToProductDto")
	@Test
	void shouldReturnListOfProductDto_whenProductsExistWithCategoryIdAndConvertFromProductToProductDto(){
		String productName="Samsung 23";
		String categoryId="categoryId";
		Long supplierId=1L;
		String productColor="red";
		String description="Very good";
		String categoryName="categoryName";
		BigDecimal price=BigDecimal.valueOf(1500);
		Product product=new Product(
				productName,
				categoryId,
				supplierId,
				productColor,
				description,
				price
		);
		Long productId=product.getId();
		ProductDto productDto=new ProductDto(
				product.getName(),
				100L,
				product.getPrice(),
				categoryName,
				product.getColor(),
				product.getDescription()
		);

		String productName2="Samsung 22";
		String productColor2="red";
		BigDecimal price2=BigDecimal.valueOf(1500);
		Product product2=new Product(
				productName2,
				categoryId,
				supplierId,
				productColor2,
				description,
				price
		);
		Long productId2=product.getId();
		ProductDto productDto2=new ProductDto(
				product2.getName(),
				100L,
				product.getPrice(),
				categoryName,
				product2.getColor(),
				product.getDescription()
		);

		List<Product> productList1=new ArrayList<>();
		productList1.add(product);
		productList1.add(product2);

		List<ProductDto> productList=new ArrayList<>();
		productList.add(productDto);
		productList.add(productDto2);

		Mockito.when(productRepository.findByCategoryId(categoryId)).thenReturn(productList1);
		Mockito.when(dtoConverter.convert(product)).thenReturn(productDto);
		Mockito.when(dtoConverter.convert(product2)).thenReturn(productDto2);

		List<ProductDto> result=productService.findProductListByCategoryId(categoryId);

		assertEquals(productList,result);

		Mockito.verify(productRepository).findByCategoryId(categoryId);
		Mockito.verify(dtoConverter).convert(product);
		Mockito.verify(dtoConverter).convert(product2);

	}

	@DisplayName("shouldReturnProductDtoWithUpdatedCategory_WhenChangedCategory")
	@Test
	void shouldReturnProductDtoWithUpdatedCategory_WhenChangedCategory(){
		String categoryId1="categoryId1";
		String categoryId2="categoryId2";
		String productName="Samsung 23";
		Long supplierId=1L;
		String productColor="red";
		String description="Very good";
		String categoryName="categoryName";
		BigDecimal price=BigDecimal.valueOf(1000);

		Product product1=new Product(
				productName,
				categoryId1,
				supplierId,
				productColor,
				description,
				price
				);

		Product product2=new Product(
				productName,
				categoryId2,
				supplierId,
				productColor,
				description,
				price
		);

		ProductDto productDto=new ProductDto(
				product2.getName(),
				100L,
				product2.getPrice(),
				categoryName,
				product2.getColor(),
				product2.getDescription()
		);
		List<Product> existingProductList=new ArrayList<>();
		existingProductList.add(product1);
		List<Product> registeredProductList=new ArrayList<>();
		registeredProductList.add(product2);
		List<ProductDto> registeredProductDtoList=List.of(productDto);

		Mockito.when(productRepository.findByCategoryId(categoryId1)).thenReturn(existingProductList);
		Mockito.when(mockProduct.updateCategoryIdOfProduct(categoryId2)).thenReturn(product2);
		Mockito.when(productRepository.save(product2)).thenReturn(product2);
		Mockito.when(dtoConverter.convert(product2)).thenReturn(productDto);

		List<ProductDto> result=productService.updateCategoryOfProductByCategoryId(categoryId1,categoryId2);

		assertEquals(registeredProductDtoList,result);

		Mockito.verify(productRepository).findByCategoryId(categoryId1);
		Mockito.verify(productRepository).save(product2);
		Mockito.verify(dtoConverter).convert(product2);

	}

	@DisplayName("shouldReturnProductPrice_WhenExistProductWithProductId")
	@Test
	void shouldReturnProductPrice_WhenExistProductWithProductId(){
		String productName="Samsung 23";
		String categoryId="categoryId";
		Long supplierId=1L;
		String productColor="red";
		String description="Very good";
		BigDecimal price=BigDecimal.valueOf(1500);
		Product product=new Product(
				productName,
				categoryId,
				supplierId,
				productColor,
				description,
				price
		);

		Long productId=product.getId();

		Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));

		BigDecimal totalPrice=productService.findProductPriceByProductId(productId);

		assertEquals(product.getPrice(),totalPrice);

		Mockito.verify(productRepository).findById(productId);

	}


	@AfterEach
	void tearDown(){

	}

}
