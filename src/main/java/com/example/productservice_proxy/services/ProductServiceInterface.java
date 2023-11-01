package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Product;

public interface ProductServiceInterface {
    String getAllProducts();

    Product getSingleProduct(Long productId);

    String deleteProduct(Long productId);

    String updateProduct(Long productId);

    String addNewProduct(ProductDto productDto);
}
