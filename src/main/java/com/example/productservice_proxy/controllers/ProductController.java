package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.clients.IClientProductDto;
import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDto;
import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.ProductServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductServiceInterface productService;
    public ProductController(ProductServiceInterface productService){

        this.productService=productService;
    }


  @GetMapping(" ")
//  public String getAllProducts() {
//
//      return "Getting all the Products";
//  }

//  public List<Product> getAllProducts() {
//        return this.productService.getAllProducts();
//    } // this is a Shortcut without creating a RESPONSE ENTITY for the below code.


    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK );



    }


    @GetMapping("/{productId}")
    //public String getSingleProduct(@PathVariable("productId") Long productId) {
    //public Product getSingleProduct(@PathVariable("productId") Long productId) {
        //productService.getSingleProduct(productId);
        //return "Returning single Product with id: " + productId;
        //String product = this.productService.getSingleProduct(productId);
        //return "Returning single Product with id: " + product;
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) {

        //ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        //ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        //return responseEntity;

        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token","hey access me");

            Product product = this.productService.getSingleProduct(productId);
            if(productId < 1) {
                throw new IllegalArgumentException("something went wrong");
            }

            //ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, headers,HttpStatus.OK);


            //return product;
            return responseEntity;
        } catch (Exception e) {
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            throw e;
        }
    }

    @PostMapping()
//    public String addNewProduct(@RequestBody ProductDto productDto){
   public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){

        Product product = getProduct(productDto);

        Product savedproduct = this.productService.addNewProduct(product);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(savedproduct, HttpStatus.OK);


//        Product product = this.productService.addNewProduct(productDto);
//        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);

        //return"Adding new Product"+productDto;
        return responseEntity;

    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId){
        return "Updating Product with id: " + productId ;
    }

    @PatchMapping("/{productId}")
    public Product patchProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Categories());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return this.productService.updateProduct(productId, product);


        //return "Patching Product with id: " + productId;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "Deleting Product with id: " + productId;
    }

    //@ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("Kuch toh phat hai", HttpStatus.INTERNAL_SERVER_ERROR);
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

