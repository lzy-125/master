package com.easy.arch.dao;

import com.easy.arch.entity.Chairs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ChairsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertUser(Chairs chairs) {
        String sql = "INSERT INTO chairs (id,name) VALUES (?,?)";
        return jdbcTemplate.update(sql, chairs.getId(), chairs.getName());
    }

    public int deleteChairsUser(Chairs chairs){
        String sql = "DELETE FROM chairs WHERE id=?";
        return jdbcTemplate.update(sql,chairs.getId());
    }

}
