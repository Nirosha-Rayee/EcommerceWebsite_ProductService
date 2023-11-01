package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Product;

import java.util.List;

public interface ProductServiceInterface {
   //String getAllProducts();
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    String deleteProduct(Long productId);

    String updateProduct(Long productId);

    Product addNewProduct(ProductDto productDto);
}
