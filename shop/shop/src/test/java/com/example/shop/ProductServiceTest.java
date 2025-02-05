package com.example.shop;

import com.example.shop.model.Product;
import com.example.shop.repo.ProductRepository;
import com.example.shop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product mockProduct;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Sample Product");
        mockProduct.setPieces(10);
        mockProduct.setPrice(100);
    }

    @Test
    public void testGetProductById_Success() {

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        Product result = productService.getProductById(1L);


        assertNotNull(result);//result null a irukka koodathu
        assertEquals(mockProduct.getId(), result.getId());
        assertEquals("Sample Product", result.getName());
        verify(productRepository, times(1)).findById(1L);
    }


}