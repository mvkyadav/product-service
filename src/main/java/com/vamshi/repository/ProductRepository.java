package com.vamshi.repository;

import com.vamshi.entity.Product;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select price from product where product_id = ?", nativeQuery = true)
    long findByProductId(long productId);

}
