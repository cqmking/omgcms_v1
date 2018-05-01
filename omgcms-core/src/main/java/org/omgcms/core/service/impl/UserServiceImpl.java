package org.omgcms.core.service.impl;

import org.omgcms.core.exception.CustomSystemException;
import org.omgcms.core.exception.ExceptionCode;
import org.omgcms.core.model.User;
import org.omgcms.core.repository.UserRepository;
import org.omgcms.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

            if (userWithScName.getUserId() != user.getUserId()) {
                throw new CustomSystemException(ExceptionCode.ERROR_USER_SCREENAME_EXIST, screenName);
            }
            if (userWithEmail.getUserId() != user.getUserId()) {
                throw new CustomSystemException(ExceptionCode.ERROR_USER_EMAIL_EXIST, email);
            }
        }

        return userRepository.save(user);
    }

    public User getUser(long userId) {
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

}
