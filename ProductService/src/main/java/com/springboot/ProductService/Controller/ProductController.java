package com.springboot.ProductService.Controller;

import com.springboot.ProductService.Model.ProductRequest;
import com.springboot.ProductService.Model.ProductResponse;
import com.springboot.ProductService.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        long productId = productService.addProduct(productRequest);
        return  new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping("/get-products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable(value = "id") Long productID){

        var product = productService.getProductById(productID);

        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable(value = "id") Long productId,
                                               @RequestParam(value = "quantity", defaultValue = "0") Long quantity){
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
