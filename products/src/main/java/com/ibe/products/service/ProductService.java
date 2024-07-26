package com.ibe.products.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibe.products.dto.ProductRequest;
import com.ibe.products.dto.ProductResponse;
import com.ibe.products.dto.PurchaseProductRequest;
import com.ibe.products.dto.PurchateProductResponse;
import com.ibe.products.exception.PurchaseProductException;
import com.ibe.products.mapper.ProductMapper;
import com.ibe.products.models.Products;
import com.ibe.products.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public Integer creatProduct(ProductRequest productRequest){
        Products product = mapper.toProducts(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductResponse> allProduct(){
        return productRepository.findAll()
        .stream()
        .map(mapper::fromProduct)
        .toList();
    }

    public ProductResponse findProductById(Integer id){
        return productRepository.findById(id)
        .map(mapper::fromProduct)
        .orElseThrow(()-> new EntityNotFoundException("product provide with by Id : "+id+" not exist"));
    }

    @Transactional(rollbackFor = PurchaseProductException.class)
    public List<PurchateProductResponse> purchateProducts(List<PurchaseProductRequest> requests){
        //recuperate the list of id purche product request
        var productId = requests.stream()
                    .map(PurchaseProductRequest::productId)
                    .toList();
        //recuperate the product thas has same id of request in the database
        var storProduct = productRepository.findAllByIdInOrderById(productId);

        if(productId.size() != storProduct.size()){
            throw new PurchaseProductException("one or more product doen't exist");
        }

        //sort the purche product request
        var storRequest = requests
                    .stream()
                    .sorted(Comparator.comparing(PurchaseProductRequest::productId))
                    .toList();

        var purchatedProducts = new ArrayList<PurchateProductResponse>();
        for(int i=0; i< storProduct.size(); i++){
            var product = storProduct.get(i);
            var productRequest = storRequest.get(i);

            if(product.getAvailableQuantity()<productRequest.quantity()){
                throw new PurchaseProductException("Insuffisante stock product with ID : "+productRequest.productId());
            }
            var newQuantity = product.getAvailableQuantity()-productRequest.quantity();
            product.setAvailableQuantity(newQuantity);
            productRepository.save(product);
            
            purchatedProducts.add(mapper.toPurchateProductResponse(product, productRequest.quantity()));
            
        }
        return purchatedProducts;
    }   

    
}
