package com.example.shop.controller;

import com.example.shop.model.Product;
import com.example.shop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    // Read all products with pagination, sorting, and search filter
    @GetMapping("/allProduct/pagination")
    public Page<Product> getAllProducts(
            @RequestParam(required = false) String searchQuery,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {

        return productService.getProducts(searchQuery, page, size, sortBy, direction);
    }
@GetMapping("/getAll")
    public List<Product> getAllProducts(){

        return productService.getAllProducts();
    }

    @GetMapping("/getById/{id}")
    public Product getProductById(@PathVariable Long id){

        return productService.getProductById(id);
    }


    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
        Product addedProduct =  productService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.OK);}


    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return "product deleted successfully with ID: " + id;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }


}