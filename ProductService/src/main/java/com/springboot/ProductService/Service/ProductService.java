package com.springboot.ProductService.Service;


import com.springboot.ProductService.Model.ProductRequest;

public interface ProductService {

    Long addProduct(ProductRequest productRequest);
}
