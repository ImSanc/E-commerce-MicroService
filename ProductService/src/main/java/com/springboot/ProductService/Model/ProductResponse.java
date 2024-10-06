package com.springboot.ProductService.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {
    private String productName;
    private Long productId;
    private Long quantity;
    private Long price;
}
