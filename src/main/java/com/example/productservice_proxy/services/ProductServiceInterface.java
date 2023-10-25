package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDto;

public interface ProductServiceInterface {
    String getAllProducts();

    String getSingleProduct(Long productId);

    String deleteProduct(Long productId);

    String updateProduct(Long productId);

    String addNewProduct(ProductDto productDto);
}
