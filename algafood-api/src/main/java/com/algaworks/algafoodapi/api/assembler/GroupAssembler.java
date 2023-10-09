package com.algaworks.algafoodapi.api.assembler;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public List<GroupOutput> toCollectionOutput(Set<Group> groups) {
        return groups.stream().map(g -> toOutput(g)).toList();
    }

    public Page<GroupOutput> fromListEntitytoPageOutput(Set<Group> groups, Pageable pageable) {
        List<GroupOutput> gOutputs = toCollectionOutput(groups);
        return new PageImpl<>(gOutputs, pageable, gOutputs.size());
    }
}
