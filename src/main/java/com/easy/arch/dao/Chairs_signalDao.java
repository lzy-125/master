package com.easy.arch.dao;


import com.easy.arch.entity.Chairs;
import com.easy.arch.entity.Chairs_signal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Chairs_signalDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Chairs_signal findByChairIdSignal(int id) {
        final Chairs_signal chairs_signal = new Chairs_signal();
        String sql = "SELECT id,state FROM chairs_signal WHERE id=? and state=0";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                chairs_signal.setId(resultSet.getInt(1));
                chairs_signal.setState(resultSet.getInt(2));

            }
        });
        return chairs_signal;
    }

    public List<Chairs_signal> findByClazzId() {
        String sql = "SELECT * FROM chairs_signal";
        final List<Chairs_signal> list =new ArrayList<Chairs_signal>();
        jdbcTemplate.query(sql, new Object[]{}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Chairs_signal chairs_signal = new Chairs_signal();
                chairs_signal.setId(resultSet.getInt(1));
                chairs_signal.setState(resultSet.getInt(2));
                list.add(chairs_signal);
            }
        });
        return list;
    }
    //查询所有数据个数
    public int findAll(){
        String sql = "select count(*) from chairs_signal where state=0";

//        此方法有两个参数
        //一个参数是sql，一个参数是根据返回值类型而定，如果是String类型就返回String类型，是Int类型就返回Int类型
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    //查询对应页的数据

    public List<Chairs_signal> queryCurrentPage(int size, int pageSize){
        String sql = " SELECT * FROM chairs_signal where state=0 LIMIT ?,?";
        final List<Chairs_signal> list =new ArrayList<Chairs_signal>();
        jdbcTemplate.query(sql, new Object[]{size,pageSize}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Chairs_signal chairs_signal = new Chairs_signal();
                chairs_signal.setId(resultSet.getInt(1));
                chairs_signal.setState(resultSet.getInt(2));
                list.add(chairs_signal);
            }
        });
        return list;
    }

    public List<Chairs_signal> findByChairsState(){
        String sql = "SELECT id,state FROM chairs_signal WHERE state=0";
        final List<Chairs_signal> list1 =new ArrayList<Chairs_signal>();
        jdbcTemplate.query(sql, new Object[]{}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Chairs_signal chairs_signal = new Chairs_signal();
                chairs_signal.setId(resultSet.getInt(1));
                chairs_signal.setState(resultSet.getInt(2));
                list1.add(chairs_signal);
            }
        });
        return list1;
    }

//    public int insertChairsUser(Chairs_signal chairs_signal){
//        String sql = "INSERT INTO user (id) VALUES (?)";
//        return jdbcTemplate.update(sql, chairs_signal.getId(),chairs_signal.getState());
//    }
    public int updateChairsState(Chairs_signal chairs_signal){
        String sql = "UPDATE chairs_signal SET state=1 WHERE id=?";
        return jdbcTemplate.update(sql,chairs_signal.getId());
    }

    public int quitChairsState(Chairs_signal chairs_signal){
        String sql = "UPDATE chairs_signal SET state=0 WHERE id=?";
        return jdbcTemplate.update(sql,chairs_signal.getId());
    }

    public List<Chairs> findChairsByName(String name){
        String sql="select * from chairs where name=?";
        final List<Chairs> list=new ArrayList<>();
        jdbcTemplate.query(sql, new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Chairs chairs=new Chairs();
                chairs.setId(rs.getInt(1));
                chairs.setName(rs.getString(2));
                list.add(chairs);

            }
        });
        return list;
    }


}
