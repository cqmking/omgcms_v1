package org.omgcms.core.service.impl;

import org.omgcms.core.exception.CustomSystemException;
import org.omgcms.core.exception.ExceptionCode;
import org.omgcms.core.model.User;
import org.omgcms.core.model.UserRole;
import org.omgcms.core.repository.UserRepository;
import org.omgcms.core.service.UserService;
import org.omgcms.kernel.util.StringPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
 * @Date created in 3:27 2018/4/6
 * @Modified By
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUserId(long userId) {
        return userRepository.findByUserId(userId);
    }

    public User findByScreenName(String screenName) {
        return userRepository.findByScreenName(screenName);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {

        String screenName = user.getScreenName();
        User userWithScName = findByScreenName(screenName);

        String email = user.getEmail();
        User userWithEmail = findByEmail(email);

        if (user.getUserId() == null || user.getUserId() <= 0) {
            //create - check email and screenName unique
            if (userWithScName != null) {
                throw new CustomSystemException(ExceptionCode.ERROR_USER_SCREENAME_EXIST, screenName);
            }
            if (userWithEmail != null) {
                throw new CustomSystemException(ExceptionCode.ERROR_USER_EMAIL_EXIST, email);
            }

        } else {

            if (userWithScName.getUserId().longValue() != user.getUserId().longValue()) {
                throw new CustomSystemException(ExceptionCode.ERROR_USER_SCREENAME_EXIST, screenName);
            }
            if (userWithEmail.getUserId().longValue() != user.getUserId().longValue()) {
                throw new CustomSystemException(ExceptionCode.ERROR_USER_EMAIL_EXIST, email);
            }
        }

        return userRepository.save(user);
    }

    public User getUser(long userId) {
        if (userId <= 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "userId");
        }
        return userRepository.getOne(userId);
    }

    public void delete(long userId) {
        if (userId <= 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "userId");
        }
        try {
            userRepository.delete(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomSystemException(ExceptionCode.ERROR_USER_ID_NOT_EXSIT, userId);
        }

    }

    public void deleteInBatch(long[] userIds) {
        Set<User> usersSet = new HashSet<User>();
        for (long userId : userIds) {
            User lcUser = userRepository.getOne(userId);
            usersSet.add(lcUser);
        }
        userRepository.deleteInBatch(usersSet);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }


    public Page<User> findAll(int pageNo, int pageSize, String orderByProperty, boolean isAsc) {

        Direction direction = Direction.ASC;

        if (!isAsc) {
            direction = Sort.Direction.DESC;
        }

        Order idOrder = new Order(direction, orderByProperty);
        Sort sort = new Sort(idOrder);

        PageRequest pageable = new PageRequest(pageNo - 1, pageSize, sort);

        Page<User> page = userRepository.findAll(pageable);

        return page;
    }


    public Page<User> getUsersByRoleId(int pageNo, int pageSize, String orderByProperty, boolean isAsc, final long roleId) {

        Specification<User> specification = new Specification<User>() {

            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Join<User, UserRole> userRoles = root.join("userRoles", JoinType.LEFT);

                Path<String> path = userRoles.get("id").get("roleId");
                return cb.equal(path, roleId);
            }

        };

        Direction direction = Direction.ASC;

        if (!isAsc) {
            direction = Direction.DESC;
        }

        Order idOrder = new Order(direction, orderByProperty);
        Sort sort = new Sort(idOrder);

        PageRequest pageable = new PageRequest(pageNo - 1, pageSize, sort);

        Page<User> page = userRepository.findAll(specification, pageable);

        return page;
    }


    public Page<User> getUnassignedRoleUsers(int pageNo, int pageSize, String orderByProperty, boolean isAsc, final long roleId) {

        Specification<User> specification = new Specification<User>() {


            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Subquery<Long> subQuery = query.subquery(Long.class);
                Root<UserRole> fromUR = subQuery.from(UserRole.class);

                Path<Long> userIdPath = fromUR.get("id").get("userId");

                subQuery.select(userIdPath).where(cb.equal(fromUR.get("id").get("roleId"), roleId));

                return cb.not(cb.in(root.get("userId")).value(subQuery));

            }
        };

        Direction direction = Direction.ASC;

        if (!isAsc) {
            direction = Direction.DESC;
        }

        Order idOrder = new Order(direction, orderByProperty);
        Sort sort = new Sort(idOrder);

        PageRequest pageable = new PageRequest(pageNo - 1, pageSize, sort);

        Page<User> page = userRepository.findAll(specification, pageable);

        return page;
    }

    public Page<User> search(int pageNo, int pageSize, String orderByProperty, boolean isAsc,
                             String screenName, String userName, String email) {

        Direction direction = Direction.ASC;

        if (!isAsc) {
            direction = Direction.DESC;
        }

        Order idOrder = new Order(direction, orderByProperty);
        Sort sort = new Sort(idOrder);

        PageRequest pageable = new PageRequest(pageNo - 1, pageSize, sort);

        if (StringUtils.isEmpty(screenName)) {
            screenName = StringPool.PERCENT;
        } else {
            screenName = StringPool.PERCENT + screenName + StringPool.PERCENT;
        }

        if (StringUtils.isEmpty(userName)) {
            userName = StringPool.PERCENT;
        } else {
            userName = StringPool.PERCENT + userName + StringPool.PERCENT;
        }

        if (StringUtils.isEmpty(email)) {
            email = StringPool.PERCENT;
        } else {
            email = StringPool.PERCENT + email + StringPool.PERCENT;
        }

        return userRepository.findByScreenNameLikeAndUserNameLikeAndEmailLike(screenName, userName, email, pageable);

    }

}
