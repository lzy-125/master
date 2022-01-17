package com.easy.arch.controller;

import com.easy.arch.entity.User;
import com.easy.arch.service.UserServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
//https://blog.csdn.net/sun1021873926/article/details/61615219
@Controller
public class LoginController {

@Autowired
private UserServiceIml userServiceIml;
//    User user = new User();

    @RequestMapping(value = "/log")
    public String findById(String username, String password, Model model,HttpSession session) {
        boolean result = userServiceIml.findUserByNameAndPassword(username,password);

        if (!username.equals("")&&!password.equals("")&&result==false) {
            model.addAttribute("msg", "用户名或密码错误");
        }

        if (result){
//            user.setUsername(username);
            session.setAttribute("name",username);
            session.setAttribute("quit","注销");
//            model.addAttribute("name",);
            return "redirect:c1";
        }

        return "login";

    }



    @RequestMapping(value = "/reg")
    public String insertUser(String username,String password,Model model){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);


        boolean register = userServiceIml.insertUserBy(user);

        if (!username.equals("")&&!password.equals("") && register==false) {
            model.addAttribute("error", "用户名已存在");
        }

        if (register){
                return "login";
        }
        return "register";
    }

    @RequestMapping(value = "/update")
    public String update(String username,String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userServiceIml.updateUser(user);
        return "login";
    }
    @RequestMapping(value = "/updateView")
    public String view(){
        return "updateUser";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }
}
