package com.easy.arch.controller;

import com.easy.arch.entity.Chairs;
import com.easy.arch.service.ChairsServiceIml;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChairsController {

    @Autowired
    ChairsServiceIml chairsServiceIml;

    @RequestMapping(value = "/insertChair_User", method = RequestMethod.GET)
    public String insertChair(int id,String time) {


//        Chairs chairsById = chairsServiceIml.insertByChairs();

        return "未找到该座位";

    }


}


