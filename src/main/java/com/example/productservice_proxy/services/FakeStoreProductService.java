package com.example.productservice_proxy.services;

import com.example.productservice_proxy.clients.IClientProductDto;
import com.example.productservice_proxy.clients.fakestore.client.FakeStoreClient;
import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDto;
import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//@Service


public class FakeStoreProductService implements ProductServiceInterface {

    private RestTemplateBuilder restTemplateBuilder;

    private FakeStoreClient fakeStoreClient;

    //@Autowired

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient){
        this.restTemplateBuilder=restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        //RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }




    @Override
    //public String getAllProducts() {
    public List<Product> getAllProducts() {
       // RestTemplate restTemplate = restTemplateBuilder.build();
        //String products = restTemplate.getForEntity("https://fakestoreapi.com/products", String.class).getBody();
        //ResponseEntity<List<ProductDto>> products = restTemplate.getForEntity("https://fakestoreapi.com/products", List<Product>.class); // instead of List , we are using array.
        //ResponseEntity<ProductDto[]> productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class);

        //Now, converting the Array into List of Products. so, we need to loop through the array and convert each element into Product.

        List<Product> answer = new ArrayList<>();

        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();

       // for(ProductDto productDto: productDtos.getBody()){
        for(FakeStoreProductDto productDto: fakeStoreProductDtos){
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
            answer.add(product);
            //answer.add(getProduct((productDto));
        }


        return answer;

    }

    @Override
    //public String getSingleProduct(Long productId) {
    public Product getSingleProduct(Long productId) {



        RestTemplate restTemplate = restTemplateBuilder.build();
        //ProductDto productDto =
            // restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class,productId).getBody();

        ResponseEntity<FakeStoreProductDto> productDto =
                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class,productId);

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
//        product.setDescription(productDto.getDescription()); //we can write this also or we can put in util class or like HELPER FUNCTION.


        //Product product = getProduct(productDto);
        Product product = getProduct(productDto.getBody());
        return product;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
    @Override
    public Product updateProduct(Long productId, Product product) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );

        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreProductDtoResponseEntity.getBody();
        return getProduct(fakeStoreProductDto1);

    }

//    @Override
//    public Product addNewProduct(IClientProductDto productDto) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        //restTemplate.postForEntity("https://fakestoreapi.com/products",productDto,ProductDto.class);
//        Product product = getProduct((FakeStoreProductDto) productDto);
//        return product;
//
//
//    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    private Product getProduct(FakeStoreProductDto productDto) {
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
