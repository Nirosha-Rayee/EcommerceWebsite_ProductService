package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service


public class ProductService implements ProductServiceInterface {

    private RestTemplateBuilder restTemplateBuilder;
    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }


    @Override
    public String getAllProducts() {
        return null;
    }

    @Override
    //public String getSingleProduct(Long productId) {
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class,productId).getBody();

        //if we put Map.class instead of ProductDto.class, we will get a map of key value pairs.
        //Map productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", Map.class,productId).getBody();
        //return productDto.toString();
        //now go to postman, get https://fakestoreapi.com/products/1 , send
        //we will get a map of key value pairs in Threads and Variables section. and check the productDto. now has to add these lines

        //ProductDto productDto1 = new ProductDto();
        //productDto1.setId(productDto.get("id").toString());

        //productDto1.setTitle(productDto.get("title").toString());
        //productDto1.setDescription(productDto.get("description").toString());
        //productDto1.setImage(productDto.get("image").toString());
        //productDto1.setPrice(Double.parseDouble(productDto.get("price").toString()));
        //productDto1.setCategory(productDto.get("category").toString());
        //productDto1.setRating(productDto.get("rating").toString());
        //return productDto1.toString();
        //if we don't use internals, this is the way to convert objects into string.


        //previous it has returns the String, but we has to return the product. so we has to change the return type of this method.
        //and change it to Product where ever it has to be changed.
        // now has to write the conversion logic from productDto to product.


//        Product product = new Product();
//        product.setId(productDto.getId());
//        product.setTitle(productDto.getTitle());
//        product.setDescription(productDto.getDescription());
//
//        product.setPrice(productDto.getPrice());
//        Categories category = new Categories();
//        category.setName(productDto.getCategory());
//        product.setCategory(category);
//        product.setImageUrl(productDto.getImage());
//        product.setDescription(productDto.getDescription());


        Product product = getProduct(productDto);
        return product;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
    @Override
    public String updateProduct(Long productId) {
        return null;
    }

    @Override
    public String addNewProduct(ProductDto productDto) {
        return null;
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());

        product.setPrice(productDto.getPrice());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        return product;
    }


}
