package com.algaworks.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.ProductAssembler;
import com.algaworks.algafoodapi.api.dto.ProductOutput;
import com.algaworks.algafoodapi.domain.model.Product;
import com.algaworks.algafoodapi.domain.service.ProductService;

/**
 * RestauranteProdutoController
 */
@RestController
@RequestMapping("/restaurantes/{restaurantID}/produtos")
public class RestaurantProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductAssembler productAssembler;

    @GetMapping
    public Page<ProductOutput> getAll(@PathVariable Long restaurantID, Pageable page) {
        return productService.findByRestaurant(restaurantID, page)
                .map(p -> productAssembler.toOutput(p));
    }

    @GetMapping("/{productID}")
    public ProductOutput getOne(
            @PathVariable final Long restaurantID,
            @PathVariable final Long productID) {
        Product product = productService.findOrFail(restaurantID, productID);
        return productAssembler.toOutput(product);
    }
}
