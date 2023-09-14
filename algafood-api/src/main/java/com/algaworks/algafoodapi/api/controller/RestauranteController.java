package com.algaworks.algafoodapi.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.dto.restaurante.RestauranteOutputDTO;
import com.algaworks.algafoodapi.api.assembler.RestauranteAssembler;
import com.algaworks.algafoodapi.api.dto.restaurante.RestauranteInputDTO;
import com.algaworks.algafoodapi.domain.service.RestauranteService;

/**
 * RestauranteController
 */
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RestauranteAssembler restauranteAssembler;

    @GetMapping
    public Page<RestauranteOutputDTO> listar(Pageable page) {
        return restauranteService.listar(page);
    }

    @GetMapping("/por-nome")
    public List<RestauranteOutputDTO> buscarPorNome(@RequestParam(value = "nome") String name) {
        return restauranteService.buscarPorNome(name);
    }

    @GetMapping("/por-nome/frete-gratis")
    public Page<RestauranteOutputDTO> buscarPorNomeEFreteGratis(Pageable page,
            @RequestParam(value = "nome") String name) {
        return restauranteService.buscarPorNomeEFreteGratis(page, name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteOutputDTO> buscar(
            @PathVariable Long id) {
        return restauranteService.buscar(id)
                .map(r -> ResponseEntity.ok(restauranteAssembler.toOutputDto(r)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<RestauranteOutputDTO> criar(
            @Valid @RequestBody RestauranteInputDTO restauranteIN) {
        return restauranteService.criar(restauranteIN).map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.internalServerError().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteOutputDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody RestauranteInputDTO restauranteIN) {
        return restauranteService.atualizar(id, restauranteIN).map(ResponseEntity::ok)
                .orElse(ResponseEntity.internalServerError().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestauranteOutputDTO> atualizarParcialmente(@PathVariable Long id,
            @RequestBody Map<String, Object> campos) {
        if (id <= 0 || campos == null || campos.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<RestauranteOutputDTO> restauranteUpdated = restauranteService.atualizarParcialmente(id, campos);
        if (restauranteUpdated.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restauranteUpdated.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestauranteOutputDTO> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(restauranteService.deletar(id).get());
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(code = HttpStatus.OK)
    public void ativar(@PathVariable("id") final Long id) {
        restauranteService.active(id);
    }

    @DeleteMapping("/{id}/ativo")
    public void desativar(@PathVariable("id") final Long id) {
        restauranteService.deactive(id);
    }
}
