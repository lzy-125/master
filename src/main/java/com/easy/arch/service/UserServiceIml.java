package com.easy.arch.service;

import com.easy.arch.dao.UserDao;

import com.easy.arch.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceIml {

    @Autowired
    UserDao userDao;

    public boolean findUserByNameAndPassword(String username, String password) {
        return userDao.findByNameAndPassword(username, password);
    }

    public User findUserByName(String username) {
        return userDao.findByName(username);
    }


    public boolean insertUserBy(User user) {

        if (user == null) {
            return false;
        }

        User userByName = findUserByName(user.getUsername());
        if (userByName != null && userByName.getUsername() != null && userByName.getUsername().equals(user.getUsername())) {
            return false;
        }

        return userDao.insertUser(user) != 0;
    }

    public int updateUser(User user){
        return userDao.updateUser(user);
    }
}


