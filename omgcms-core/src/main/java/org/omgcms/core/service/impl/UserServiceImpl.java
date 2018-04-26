package org.omgcms.core.service.impl;

import org.omgcms.core.service.UserService;
import org.omgcms.core.repository.UserRepository;
import org.omgcms.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 3:27 2018/4/6
 * @Modified By
 */
@Transactional
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

    public User save(User user){
        return userRepository.save(user);
    }

    public User getUser(long userId) {
        return userRepository.getOne(userId);
    }

    public void delete(long userId) {
        userRepository.delete(userId);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

}
