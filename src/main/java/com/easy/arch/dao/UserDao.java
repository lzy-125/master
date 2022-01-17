package com.easy.arch.dao;


import com.easy.arch.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean findByNameAndPassword(String username, String password) {

        final User user = new User();
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        jdbcTemplate.query(sql, new Object[]{username, password}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUsername(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
            }
        });
        if (!username.equals(user.getUsername()) || !password.equals(user.getPassword()) || username == "" || password == "") {
            return false;
        }
        return true;
    }

    public User findByName(String username) {

        final User user = new User();
        String sql = "SELECT username FROM user WHERE username=?";
        jdbcTemplate.query(sql, new Object[]{username}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUsername(resultSet.getString(1));
            }
        });
        return user;
    }

    public int insertUser(User user) {
        String sql = "INSERT INTO user (username,password) VALUES (?,?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }

    public int updateUser(User user){
        String sql = "UPDATE user SET password=? WHERE username=?";
        return jdbcTemplate.update(sql,user.getPassword(),user.getUsername());
    }

}
