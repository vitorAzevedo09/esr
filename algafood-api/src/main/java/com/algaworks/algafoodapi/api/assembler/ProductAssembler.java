package com.algaworks.algafoodapi.api.assembler;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.ProductOutput;
import com.algaworks.algafoodapi.domain.model.Product;

import java.util.List;

/**
 * ProdutoAssembler
 */
@Component
public class ProductAssembler {

    public  ProductOutput toOutput(Product product) {
        return new ProductOutput(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getActive());
    }


    public Page<Product> listToPage(List<Product> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }
}
