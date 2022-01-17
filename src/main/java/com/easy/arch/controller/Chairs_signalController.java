package com.easy.arch.controller;


import com.easy.arch.entity.Chairs;
import com.easy.arch.entity.Chairs_signal;
import com.easy.arch.entity.Page;
import com.easy.arch.entity.User;
import com.easy.arch.service.ChairsServiceIml;
import com.easy.arch.service.Chairs_signalServiceIml;
import com.easy.arch.service.UserServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Chairs_signalController {

    @Autowired
    Chairs_signalServiceIml chairs_signalServiceIml;

    @Autowired
    ChairsServiceIml chairsServiceIml;

    @Autowired
    UserServiceIml userServiceIml;

//    User user = new User();


    //根据座位号搜索
    @RequestMapping(value = "/c2")
    public String queryChairsId(int searchById ,HttpServletRequest request){
        Chairs_signal chairs_signal = chairs_signalServiceIml.findChairsId(searchById);
        List<Chairs_signal> list=new ArrayList<>();
        if (chairs_signal.getId()==0){
            return "forward:c1";
        }
        list.add(chairs_signal);

        request.setAttribute("list",list);

        return "forward:c1";
    }


    //显示所有座位
    @RequestMapping(value = "/c1")
    public String list(Model model,HttpServletRequest request) {
//       List<Chairs_signal> chairs_signals =chairs_signalServiceIml.findChairsById();

//       model.addAttribute("showAll",chairs_signals);

        model.addAttribute("login","登录/注册");

        int pageSize = 6; //当前页显示数据个数
        int currentPage = -1; //当前页码数
        int totalCount= chairs_signalServiceIml.queryAll(); //数据总数
        int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1; //总共要分的页数

        String current = request.getParameter("currentPage");

        if(current==null){
            currentPage = 1;
        }else {
            currentPage=Integer.parseInt(current);
        }
        if(currentPage<1){
            currentPage = 1;
        }else if(currentPage>pageSize){
            currentPage =totalPage ;
        }
//        List<Chairs_signal> chairs = (List<Chairs_signal>) request.getAttribute("chairs");
        List<Chairs_signal> chairs = (List<Chairs_signal>) request.getAttribute("list");

        if(chairs == null){
           int size=(currentPage-1)*pageSize;
            chairs = chairs_signalServiceIml.queryCurrentPage(size,pageSize);
        }
        Page page = new Page(currentPage,pageSize,totalCount,totalPage,chairs);
        model.addAttribute("page",page);
       return "show";

    }

    //显示未被占用座位
    @RequestMapping(value = "/c3")
    public String showUnLackChairs(HttpServletRequest request){

        List<Chairs_signal> list = chairs_signalServiceIml.findChairsByState();

        request.setAttribute("list",list);

        return "forward:c1";
    }
    //显示所有座位按钮
    @RequestMapping(value = "/c4")
    public String showAllChairs(){
//        List<Chairs_signal> list =chairs_signalServiceIml.findChairsById();
//
//        request.setAttribute("list",list);


        return "forward:c1";
    }
    //抢座触发事件
    @RequestMapping(value = "/c5")
    public String insertChairsUser(int id,String name){

        Chairs chairs = new Chairs();
        chairs.setId(id);
        chairs.setName(name);

        Chairs_signal chairs_signal = new Chairs_signal();
        chairs_signal.setId(id);

        int result = chairsServiceIml.insertByChairs(chairs);
        int result2 = chairs_signalServiceIml.UpdateChairs(chairs_signal);

        return "showState";
    }
    @RequestMapping("/exitChair")
    public String exitChair(int id ,String name){

        Chairs chairs = new Chairs();
        chairs.setId(id);
        chairs.setName(name);

        Chairs_signal chairs_signal = new Chairs_signal();
        chairs_signal.setId(id);

        chairs_signalServiceIml.quitChairs(chairs_signal);
        chairsServiceIml.deleteChairsUser(chairs);

        return "forward:c1";
    }

    @RequestMapping("/a1")
    public String login(){
        return "login";
    }

    @RequestMapping("/center")
    public String Center(){
        return "Center";
    }

    @RequestMapping("/showMyChairs")
    public String showMyChairs(Model model,HttpSession session){
        List<Chairs> list=chairs_signalServiceIml.findChairsByName((String) session.getAttribute("name"));
        model.addAttribute("list",list);
        model.addAttribute("name",session.getAttribute("name"));
        return "showMyChairs";
    }



}
