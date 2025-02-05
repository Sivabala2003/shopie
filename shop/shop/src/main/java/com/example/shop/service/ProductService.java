package com.example.shop.service;

import com.example.shop.model.Product;
import com.example.shop.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Read all products with pagination, sorting, and search filter
    public Page<Product> getProducts(String searchQuery, int page, int size, String sortBy, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sortBy);
        if (searchQuery != null && !searchQuery.isEmpty()) {
            return productRepository.findByNameContainingIgnoreCase(searchQuery, pageable);
        }
        return productRepository.findAll(pageable);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("product  id" + id + "not found"));
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }


    public Product updateProduct(Long id, Product updatedProductDetails) {
        // Find the existing book by ID
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(updatedProductDetails.getName());
            existingProduct.setPieces(updatedProductDetails.getPieces());
            existingProduct.setPrice(updatedProductDetails.getPrice());

            // Save updated entity
            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new IllegalStateException("product not found with id " + id));
    }


    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("product not found with id: " + id);
        }
    }


}



