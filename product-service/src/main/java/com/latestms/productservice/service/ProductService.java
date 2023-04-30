package com.latestms.productservice.service;

import com.latestms.productservice.dto.ProductRequest;
import com.latestms.productservice.dto.ProductResponse;
import com.latestms.productservice.model.Product;
import com.latestms.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
       Product newProduct= Product.builder()
                .id(productRequest.getId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();
       productRepository.save(newProduct);
       log.info("Product is save : {} ",newProduct.getId());
    }

    public List<ProductResponse> getAllProduct() {
        List<Product> products=productRepository.findAll();
//       return products.stream().map(product->mapToProductResponse(product)).collect(Collectors.toList());
       return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse=ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        return productResponse;
    }
}
