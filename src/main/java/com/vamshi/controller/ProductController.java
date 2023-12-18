package com.vamshi.controller;

import com.vamshi.entity.Product;
import com.vamshi.model.ProductRequest;
import com.vamshi.model.ProductResponse;
import com.vamshi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    public ProductService service;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody ProductRequest productRequest) {
        long productId = service.createProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getByProductId(@PathVariable long id) {
        return service.getByProductId(id);
    }

    @PutMapping("/reduceQuantity/{id}")
    public Product reduceQuantity(@PathVariable long id,
                                               @RequestParam long quantity) {
        return service.reduceQuantity(id, quantity);
    }
    @GetMapping("/amount/{id}")
    public ResponseEntity<Long> getAmount(@PathVariable long id) {
        long amount = service.getAmount(id);
        return new ResponseEntity<>(amount, HttpStatus.OK);
    }
}
