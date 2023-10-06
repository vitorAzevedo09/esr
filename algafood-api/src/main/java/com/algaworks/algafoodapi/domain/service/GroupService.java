
package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.exception.GroupNotFoundException;
import com.algaworks.algafoodapi.domain.model.Group;
import com.algaworks.algafoodapi.domain.model.Permission;
import com.algaworks.algafoodapi.domain.repository.GroupRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PermissionService pService;

    public Group findOrFail(final Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }

    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Transactional
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    public Group update(final Long id, Group groupInput) {
        if (!groupRepository.existsById(id)) {
            throw new GroupNotFoundException(id);
        }
        groupInput.setId(id);
        return groupRepository.saveAndFlush(groupInput);
    }

    @Transactional
    public void delete(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            groupRepository.flush();
        }
        throw new GroupNotFoundException(id);
    }

    @Transactional
    public void addPermission(final Long groupID, final Long permissionID) {
        Group group = findOrFail(groupID);
        Permission permission = pService.findOrFail(permissionID);
        group.addPermission(permission);
    }

    @Transactional
    public void removePermission(final Long groupID, final Long permissionID) {
        Group group = findOrFail(groupID);
        Permission permission = pService.findOrFail(permissionID);
        group.removePermission(permission);
    }
}
