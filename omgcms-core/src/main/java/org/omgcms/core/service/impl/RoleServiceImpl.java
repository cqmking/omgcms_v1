package org.omgcms.core.service.impl;

import org.omgcms.core.exception.CustomSystemException;
import org.omgcms.core.exception.ExceptionCode;
import org.omgcms.core.model.Role;
import org.omgcms.core.model.UserRole;
import org.omgcms.core.repository.RoleRepository;
import org.omgcms.core.service.RoleService;
import org.omgcms.kernel.util.StringPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.Set;


/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 2:34 2018/4/7
 * @Modified By
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role save(Role role) {
        final String name = role.getName();
        final String roleKey = role.getRoleKey();
        Role roleWithName = roleRepository.findByName(name);
        Role roleWithKey = roleRepository.findByRoleKey(roleKey);

        if (role.getRoleId() == null || role.getRoleId() <= 0) {
            //添加
            if (roleWithName != null) {
                throw new CustomSystemException(ExceptionCode.ERROR_ROLE_NAME_EXIST, name);
            }
            if (roleWithKey != null) {
                throw new CustomSystemException(ExceptionCode.ERROR_ROLE_ROLEKEY_EXIST, roleKey);
            }
        } else {
            //修改
            if (roleWithName.getRoleId().longValue() != role.getRoleId().longValue()) {
                throw new CustomSystemException(ExceptionCode.ERROR_ROLE_NAME_EXIST, name);
            }
            if (roleWithKey.getRoleId().longValue() != role.getRoleId().longValue()) {
                throw new CustomSystemException(ExceptionCode.ERROR_ROLE_ROLEKEY_EXIST, roleKey);
            }
        }

        return roleRepository.save(role);
    }

    public Role getRole(long roleId) {
        return roleRepository.getOne(roleId);
    }

    public void delete(long roleId) {
        roleRepository.delete(roleId);
    }

    public void deleteInBatch(long[] roleIds) {
        Set<Role> rolesSet = new HashSet<Role>();
        for (long roleId : roleIds) {
            Role lcRole = roleRepository.getOne(roleId);
            rolesSet.add(lcRole);
        }
        roleRepository.deleteInBatch(rolesSet);
    }


    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role findByRoleKey(String roleKey) {
        return roleRepository.findByRoleKey(roleKey);
    }

    public Page<Role> findAll(int pageNo, int pageSize, String orderByProperty, boolean isAsc) {

        Sort.Direction direction = Sort.Direction.ASC;

        if (!isAsc) {
            direction = Sort.Direction.DESC;
        }

        Sort.Order idOrder = new Sort.Order(direction, orderByProperty);
        Sort sort = new Sort(idOrder);

        PageRequest pageable = new PageRequest(pageNo - 1, pageSize, sort);

        Page<Role> page = roleRepository.findAll(pageable);

        return page;
    }


    public Page<Role> getRolesByUserId(int pageNo, int pageSize, String orderByProperty, boolean isAsc, final long userId) {

        Specification<Role> specification = new Specification<Role>() {

            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Join<Role, UserRole> userRoles = root.join("userRoles", JoinType.LEFT);

                Path<String> path = userRoles.get("id").get("userId");
                return cb.equal(path, userId);
            }
        };

        Sort.Direction direction = Sort.Direction.ASC;

        if (!isAsc) {
            direction = Sort.Direction.DESC;
        }

        Sort.Order idOrder = new Sort.Order(direction, orderByProperty);
        Sort sort = new Sort(idOrder);

        PageRequest pageable = new PageRequest(pageNo - 1, pageSize, sort);

        Page<Role> page = roleRepository.findAll(specification, pageable);

        return page;
    }

    public Page<Role> getUnassignedUserRoles(int pageNo, int pageSize, String orderByProperty, boolean isAsc, final long userId) {


        Specification<Role> specification = new Specification<Role>() {

            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Subquery<Long> subQuery = query.subquery(Long.class);
                Root<UserRole> fromUR = subQuery.from(UserRole.class);
                Path<Long> roleIdPath = fromUR.get("id").get("roleId");
                subQuery.select(roleIdPath).where(cb.equal(fromUR.get("id").get("userId"), userId));

                return cb.not(cb.in(root.get("roleId")).value(subQuery));

            }
        };

        Sort.Direction direction = Sort.Direction.ASC;

        if (!isAsc) {
            direction = Sort.Direction.DESC;
        }

        Sort.Order idOrder = new Sort.Order(direction, orderByProperty);
        Sort sort = new Sort(idOrder);

        PageRequest pageable = new PageRequest(pageNo - 1, pageSize, sort);

        Page<Role> page = roleRepository.findAll(specification, pageable);

        return page;

    }

    public Page<Role> findByNameLikeAndRoleKeyLike(int pageNo, int pageSize, String orderByProperty, boolean isAsc,
                                                   String name, String roleKey) {

        Sort.Direction direction = Sort.Direction.ASC;

        if (!isAsc) {
            direction = Sort.Direction.DESC;
        }

        Sort.Order idOrder = new Sort.Order(direction, orderByProperty);
        Sort sort = new Sort(idOrder);

        PageRequest pageable = new PageRequest(pageNo - 1, pageSize, sort);

        if (StringUtils.isEmpty(name)) {
            name = StringPool.PERCENT;
        } else {
            name = StringPool.PERCENT + name + StringPool.PERCENT;
        }

        if (StringUtils.isEmpty(roleKey)) {
            roleKey = StringPool.PERCENT;
        } else {
            roleKey = StringPool.PERCENT + roleKey + StringPool.PERCENT;
        }

        return roleRepository.findByNameLikeAndRoleKeyLike(name, roleKey, pageable);
    }
}
