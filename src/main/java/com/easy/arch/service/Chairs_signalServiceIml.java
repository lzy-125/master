package com.easy.arch.service;

import com.easy.arch.dao.Chairs_signalDao;
import com.easy.arch.entity.Chairs;
import com.easy.arch.entity.Chairs_signal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Chairs_signalServiceIml {

    @Autowired
    Chairs_signalDao chairs_signalDao;


    public List<Chairs> findChairsByName(String name){
        return chairs_signalDao.findChairsByName(name);
    }




    public List<Chairs_signal> findChairsById(){
        return chairs_signalDao.findByClazzId();

    }
   public Chairs_signal findChairsId(int id){
        return chairs_signalDao.findByChairIdSignal(id);
   }

   public List<Chairs_signal> findChairsByState(){
        return chairs_signalDao.findByChairsState();
   }

//   public int insertChairs(Chairs_signal chairs_signal){
//        return chairs_signalDao.insertChairsUser(chairs_signal);
//   }

   public int UpdateChairs(Chairs_signal chairs_signal){
        return chairs_signalDao.updateChairsState(chairs_signal);
   }

   public int quitChairs(Chairs_signal chairs_signal){
        return chairs_signalDao.quitChairsState(chairs_signal);
   }

   //查询所有数据的个数
   public int queryAll(){
        return chairs_signalDao.findAll();
   }

   //查询当前页数据
    public List<Chairs_signal> queryCurrentPage(int size,int pageSize){
        return chairs_signalDao.queryCurrentPage(size,pageSize);
    }
}
