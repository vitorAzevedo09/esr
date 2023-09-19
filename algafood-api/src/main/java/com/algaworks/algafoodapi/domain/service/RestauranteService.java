package com.algaworks.algafoodapi.domain.service;

import static com.algaworks.algafoodapi.infrastructure.repository.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafoodapi.infrastructure.repository.RestauranteSpecs.comNomeSemelhante;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafoodapi.api.assembler.RestauranteAssembler;
import com.algaworks.algafoodapi.api.dto.restaurante.RestauranteInputDTO;
import com.algaworks.algafoodapi.api.dto.restaurante.RestauranteOutputDTO;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.exception.RestauranteNotFoundException;

/**
 * RestauranteService
 */
@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private RestauranteAssembler restauranteAssembler;

    public Page<RestauranteOutputDTO> listar(Pageable page) {
        return restauranteAssembler.toPageOutputDto(restauranteRepository.findAll(page));
    }

    public List<RestauranteOutputDTO> buscarPorNome(String name) {
        List<Restaurante> restaurantes = restauranteRepository.consultarPorNome(name);
        return restaurantes.stream().map(r -> restauranteAssembler.toOutputDto(r)).toList();
    }

    public Page<RestauranteOutputDTO> buscarPorNomeEFreteGratis(Pageable page, String name) {
        Page<Restaurante> restaurantes = restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(name)),
                page);
        return restaurantes.map(r -> restauranteAssembler.toOutputDto(r));
    }

    public Restaurante findOrFail(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNotFoundException(id));
    }

    @Transactional
    public Optional<RestauranteOutputDTO> criar(RestauranteInputDTO restauranteIN) {
        Restaurante restaurante = restauranteRepository.save(restauranteAssembler.toEntity(restauranteIN));
        Optional<Cozinha> cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId());
        restaurante.setCozinha(cozinha.get());
        RestauranteOutputDTO restauranteOUT = restauranteAssembler.toOutputDto(restaurante);
        return Optional.of(restauranteOUT);
    }

    @Transactional
    public Optional<RestauranteOutputDTO> atualizar(Long id, RestauranteInputDTO restauranteIN) {
        Restaurante restauranteToUpdate = restauranteAssembler.toEntity(restauranteIN);
        restauranteToUpdate.setId(id);

        Restaurante restauranteUpdated = restauranteRepository.saveAndFlush(restauranteToUpdate);

        RestauranteOutputDTO restauranteOUT = restauranteAssembler.toOutputDto(restauranteUpdated);

        return Optional.of(restauranteOUT);
    }

    @Transactional
    public Optional<RestauranteOutputDTO> atualizarParcialmente(Long id, Map<String, Object> campos) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);

        if (restaurante.isEmpty()) {
            return Optional.empty();
        }

        campos.forEach((k, v) -> {
            Field campo = ReflectionUtils.findField(Restaurante.class, k);
            campo.setAccessible(true);
            if (v instanceof Double) {
                ReflectionUtils.setField(campo, restaurante.get(), BigDecimal.valueOf((Double) v));
            } else {
                ReflectionUtils.setField(campo, restaurante.get(), v);
            }
        });
        Restaurante restauranteUpdated = restauranteRepository.save(restaurante.get());
        return Optional.of(restauranteAssembler.toOutputDto(restauranteUpdated));
    }

    @Transactional
    public Optional<RestauranteOutputDTO> deletar(Long id) {
        Optional<Restaurante> restauranteDB = restauranteRepository.findById(id);

        restauranteRepository.deleteById(id);

        RestauranteOutputDTO restauranteOUT = restauranteAssembler.toOutputDto(restauranteDB.get());
        return Optional.of(restauranteOUT);
    }

    @Transactional
    public void active(final Long id) {
        Restaurante restaurante = findOrFail(id);
        restaurante.active();
    }

    @Transactional
    public void deactive(final Long id) {
        Restaurante restaurante = findOrFail(id);
        restaurante.deactive();
    }

    @Transactional
    public void removePaymentMethod(Long idRestaurant, Long idPaymentMethod) {
        Restaurante restaurant = findOrFail(idRestaurant);
        FormaPagamento paymentMethod = formaPagamentoRepository.findById(idPaymentMethod)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        restaurant.getFormasPagamento().remove(paymentMethod);

    }
}
