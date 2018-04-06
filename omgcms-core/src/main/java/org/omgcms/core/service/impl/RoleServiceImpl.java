package org.omgcms.core.service.impl;

import org.omgcms.core.model.Role;
import org.omgcms.core.repository.RoleRepository;
import org.omgcms.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 2:34 2018/4/7
 * @Modified By
 */
@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public Role getRole(long roleId){
        return roleRepository.getOne(roleId);
    }

    public void delete(long roleId){
        roleRepository.delete(roleId);
    }

}
