package com.vamshi.service;

import com.vamshi.entity.Product;
import com.vamshi.model.ProductRequest;
import com.vamshi.model.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    public long createProduct(ProductRequest request);

    List<Product> getAllProducts();

    ProductResponse getByProductId(long id);

    Product reduceQuantity(long id, long quantity);

    long getAmount(long id);
}
