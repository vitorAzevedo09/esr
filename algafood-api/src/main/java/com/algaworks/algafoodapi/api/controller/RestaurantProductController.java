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
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.service.RestaurantService;

import java.util.List;

/**
 * RestauranteProdutoController
 */
@RestController
@RequestMapping("/restaurantes/{restaurantId}/produtos/")
public class RestaurantProductController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductAssembler productAssembler;

    @GetMapping
    public Page<ProductOutput> getAll(@PathVariable Long restaurantId, Pageable page) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);
        List<Product> products = restaurant.getProducts();
        return productAssembler.listToPage(products, page)
                .map((p) -> productAssembler.toOutput(p));
    }
}
