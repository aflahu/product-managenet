package com.example.productManagement.service;

import com.example.productManagement.constant.ProductStatus;
import com.example.productManagement.model.Product;
import com.example.productManagement.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findByStatus(ProductStatus.APPROVED);
    }

    public List<Product> getPendingProducts() {
        return productRepository.findByStatus(ProductStatus.PENDING);
    }

    public Product addProduct(Product product) {
        product.setStatus(ProductStatus.PENDING);
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(UUID id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
           product.setProductName(updatedProduct.getProductName());
           product.setPrice(updatedProduct.getPrice());
           product.setProductDescription(updatedProduct.getProductDescription());
            return productRepository.save(product);
        });
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> approveProduct(UUID id) {
        return productRepository.findById(id).map(product -> {
            product.setStatus(ProductStatus.APPROVED);
            return productRepository.save(product);
        });
    }

    public Optional<Product> rejectProduct(UUID id) {
        return productRepository.findById(id).map(product -> {
            product.setStatus(ProductStatus.REJECTED);
            return productRepository.save(product);
        });
    }
}
