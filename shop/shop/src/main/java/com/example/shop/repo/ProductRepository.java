package com.example.shop.repo;

import com.example.shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Optional method for searching products by name
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
