package com.smhrd.botbuddies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.botbuddies.entity.Order;
import com.smhrd.botbuddies.entity.Review;
import com.smhrd.botbuddies.entity.ReviewMa;
import com.smhrd.botbuddies.entity.ReviewRequest;
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
    


    @RequestMapping("/reviewWrite")
    public void reviewWrite(@RequestBody ReviewRequest requestData) {
        String id = requestData.getId();
        String storeName = requestData.getStoreName();
        int orderNum = requestData.getOrderNum();
        int rating = requestData.getRating();
        List<String> photos = requestData.getPhotos();
        String reviewText = requestData.getReviewText();
        String reviewTitle = requestData.getReviewTitle();
        System.out.println(id);
        System.out.println(storeName);
        System.out.println(rating);
        System.out.println(reviewText);
        System.out.println(reviewTitle);
        System.out.println(photos);
        String storeSeq = mapper.storeSeq(storeName);
        String nick = mapper.userNick(id);
        System.out.println(storeSeq);
       
        mapper.reviewWrite(nick, storeSeq, orderNum, rating,reviewTitle,reviewText);
        Integer review_seq = mapper.reviewSeq(nick, storeSeq, reviewTitle, reviewText);
        System.out.println(review_seq);
        for(String i : photos){
            mapper.reviewImg(review_seq,i);
        }
        

    }
    

    @RequestMapping("/reviewModify")
    public List<ReviewMa> requestMethodName(@RequestBody Map<String,String> requestData) {
        System.out.println("구분하기 -----------------------");
        String id = requestData.get("id");
        System.out.println(id);
        List<ReviewMa> ReviewModify = mapper.reviewModify(id);
        System.out.println(ReviewModify);
        return ReviewModify;
    }
    
}
