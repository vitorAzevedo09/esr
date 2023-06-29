package com.algaworks.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.GrupoAssembler;
import com.algaworks.algafoodapi.api.dto.grupo.GrupoDTO;
import com.algaworks.algafoodapi.api.dto.grupo.GrupoInputDTO;
import com.algaworks.algafoodapi.domain.model.Grupo;
import com.algaworks.algafoodapi.domain.repository.GrupoRepository;
import com.algaworks.algafoodapi.domain.service.GrupoService;

/**
 * GrupoController
 */
@RestController
@RequestMapping("/grupos")
public class GrupoController {

  @Autowired
  private GrupoRepository grupoRepository;

  @Autowired
  private GrupoAssembler grupoAssembler;

  @Autowired
  private GrupoService grupoService;

  @GetMapping
  public List<GrupoDTO> listar() {
    List<Grupo> todosGrupos = grupoRepository.findAll();

    return grupoAssembler.toCollectionDTO(todosGrupos);
  }

  @GetMapping("/{grupoId}")
  public GrupoDTO buscar(@PathVariable Long grupoId) {
    Grupo grupo = grupoService.buscarOuFalhar(grupoId);

    return grupoAssembler.toOutputDTO(grupo);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public GrupoDTO adicionar(@RequestBody @Valid GrupoInputDTO grupoInput) {
    Grupo grupo = grupoAssembler.toModel(grupoInput);

    grupo = grupoService.salvar(grupo);

    return grupoAssembler.toOutputDTO(grupo);
  }

  @PutMapping("/{grupoId}")
  public GrupoDTO atualizar(@PathVariable Long grupoId,
      @RequestBody @Valid GrupoDTO grupoInput) {
    Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);

    grupoAssembler.copyToModel(grupoInput, grupoAtual);

    grupoAtual = grupoService.salvar(grupoAtual);

    return grupoAssembler.toOutputDTO(grupoAtual);
  }

  @DeleteMapping("/{grupoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long grupoId) {
    grupoService.excluir(grupoId);
  }

}
