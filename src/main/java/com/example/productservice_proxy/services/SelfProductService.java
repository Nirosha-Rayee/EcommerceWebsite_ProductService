package com.example.productservice_proxy.services;

import com.example.productservice_proxy.clients.IClientProductDto;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.repositries.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SelfProductService implements ProductServiceInterface{

    ProductRepo productRepo;

    public SelfProductService(ProductRepo productRepo){
        this.productRepo=productRepo;
    }//constructor


    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

//    @Override
//    public Product addNewProduct(IClientProductDto productDto) {
//        return null;
//    }  // has to remove.


    @Override
    public Product addNewProduct(Product product) {
        this.productRepo.save(product);
        return product;

        //return null;
    }
}
