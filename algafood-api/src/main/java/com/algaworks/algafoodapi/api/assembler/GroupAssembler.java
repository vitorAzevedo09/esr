package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.GroupInput;
import com.algaworks.algafoodapi.api.dto.GroupOutput;
import com.algaworks.algafoodapi.domain.model.Group;

/**
 * GrupoAssembler
 */
@Component
public class GroupAssembler {

    public GroupOutput toOutput(Group group) {
        return new GroupOutput(group.getId(), group.getName());
    }

    public Group toEntity(GroupInput groupInput) {
        Group group = new Group();
        group.setName(groupInput.name());
        return group;
    }

}
