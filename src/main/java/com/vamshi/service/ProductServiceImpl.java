package com.vamshi.service;

import com.vamshi.entity.Product;
import com.vamshi.exception.ProductServiceCustomException;
import com.vamshi.model.ProductRequest;
import com.vamshi.model.ProductResponse;
import com.vamshi.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public long createProduct(ProductRequest request) {

        log.info("Adding Product....");
        Product product = repository.save(Product
                .builder()
                .productName(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build());
        log.info("Product saved with id : " + product.getProductId());
        return product.getProductId();
    }

    @Override
    public List<Product> getAllProducts() {
        log.info("Fetching all the products");
        return repository.findAll();
    }

    @Override
    public ProductResponse getByProductId(long id) {
        log.info("Finding by id : " + id);
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductServiceCustomException("Product not found with id : " + id, "PRODUCT_NOT_FOUND"));

        ProductResponse response = new ProductResponse();
        BeanUtils.copyProperties(product, response);

        return response;
    }

    @Override
    public Product reduceQuantity(long id, long quantity) {

        log.info("Reduce Quantity {} for id {}", quantity, id);
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product not available for the given product Id",
                        "PRODUCT_NOT_FOUND"
                ));

        if (product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }
        product.setQuantity(product.getQuantity() - quantity);
        repository.save(product);
        log.info("Product quantity updated successfully");
        return product;
    }

    @Override
    public long getAmount(long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product not available for the given product Id",
                        "PRODUCT_NOT_FOUND"
                ));

        return repository.findByProductId(id);
    }
}
