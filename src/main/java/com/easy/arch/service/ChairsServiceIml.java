package com.easy.arch.service;

import com.easy.arch.dao.ChairsDao;
import com.easy.arch.entity.Chairs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class ChairsServiceIml {
    @Autowired
    ChairsDao chairsDao;


    public int insertByChairs(Chairs chairs){
        return chairsDao.insertUser(chairs);
    }

    public int deleteChairsUser(Chairs chairs){
        return chairsDao.deleteChairsUser(chairs);
    }

}
