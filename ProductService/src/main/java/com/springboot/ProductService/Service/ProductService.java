package com.springboot.ProductService.Service;


import com.springboot.ProductService.Model.ProductRequest;
import com.springboot.ProductService.Model.ProductResponse;

public interface ProductService {

    Long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long productId);
}
