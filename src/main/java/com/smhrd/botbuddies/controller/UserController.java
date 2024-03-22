package com.smhrd.botbuddies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.botbuddies.entity.Order;
import com.smhrd.botbuddies.entity.User;
import com.smhrd.botbuddies.mapper.UserMapper;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {

    @Autowired
    private UserMapper mapper;

    @RequestMapping("/user_info")
    public List<User> user_info(@RequestBody Map<String, String> requestData) {
        System.out.println(requestData.get("data"));
        List<User> info = mapper.selectUser(); 
        System.out.println(info.toString());
        return  info;
    }


    @RequestMapping("/signin")
    public List<User> signin(@RequestBody Map<String, String> requestData){
        String id = requestData.get("id");
        String password = requestData.get("password");

        System.out.println("Received ID: " + id);
        System.out.println("Received password: " + password);

        List<User> userInfo = mapper.signin(id, password);

        if (userInfo != null  && !userInfo.isEmpty()) {
            System.out.println("Received ID: " + userInfo);
            return userInfo;
        } else { 
            
            return null;
        }
    }
    
    @RequestMapping("/favorite")
    public List<User> favofite(@RequestBody Map<String, String> requestData){
        String id = requestData.get("id");
        System.out.println("Received ID: " + id);
        List<User> userInfo = mapper.favorite(id);
        System.out.println(userInfo);
        return userInfo;
    }


    @RequestMapping("/orderlist")
    public List<Order> orderlist(@RequestBody Map<String,String> requestData) {
       String id = requestData.get("id");
       System.out.println(id);
       List<Order> OrderList = mapper.orderlist(id);
       System.out.println("확인"+OrderList);
       
        return OrderList;
    }
    
}
