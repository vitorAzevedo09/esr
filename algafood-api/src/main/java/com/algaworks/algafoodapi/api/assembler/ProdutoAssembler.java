package com.algaworks.algafoodapi.api.assembler;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.produto.ProdutoOutputDTO;
import com.algaworks.algafoodapi.domain.model.Produto;
import java.util.List;

/**
 * ProdutoAssembler
 */
@Component
public class ProdutoAssembler {

    public ProdutoOutputDTO toDTO(Produto produtoDB) {
        return new ProdutoOutputDTO(
                produtoDB.getId(),
                produtoDB.getNome(),
                produtoDB.getDescricao(),
                produtoDB.getPreco(),
                produtoDB.getAtivo());
    }

    public Page<ProdutoOutputDTO> toPageDTO(Page<Produto> page) {
        return page.map(this::toDTO);
    }

    public Page<Produto> listToPage(List<Produto> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }
}
