package com.smhrd.botbuddies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.botbuddies.entity.INQUIRIES;
import com.smhrd.botbuddies.entity.Notification;
import com.smhrd.botbuddies.entity.Order;
import com.smhrd.botbuddies.entity.Reservation;
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
        System.out.println("들어왔음");
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
        System.out.println(storeSeq);
       
        mapper.reviewWrite(id, storeSeq, orderNum, rating,reviewTitle,reviewText);
        Integer review_seq = mapper.reviewSeq(id, storeSeq, reviewTitle, reviewText);
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
    

    @RequestMapping("/reviewDelete")
public ResponseEntity<String> reviewDelete(@RequestBody Map<String, String> requestData) {
    String review_seq = requestData.get("reviewSeq");
    System.out.println("확인이요---------------------------" + review_seq);
    mapper.deleteImg(review_seq);
    mapper.reviewDelete(review_seq);
    return ResponseEntity.ok("리뷰가 삭제되었습니다."); // 성공 메시지 반환
}

    @RequestMapping("/reservaList")
    public List<Reservation> ReservaList(@RequestBody Map<String, String> requestData) {
        String id = requestData.get("id");
        System.out.println("들어옴 ------------------------------" +id);
        List<Reservation> reservation = mapper.reservaList(id);
        System.out.println(reservation);
        return reservation;
    }
    

    @RequestMapping("/reserveCancel")
    public void reserveCancel(@RequestBody Map<String, String> requestData) {
        String reserve_seq = requestData.get("id");
        System.out.println(reserve_seq);
        mapper.reserveCancle(reserve_seq);

    }
    
    @RequestMapping("/userInquiry")
    public void userInquiry(@RequestBody Map<String, String> requestData) {
        String id = requestData.get("id");
        String title = requestData.get("title");
        String content = requestData.get("content");
        System.out.println(id);
        System.out.println(title);
        System.out.println(content);
        mapper.userInquiry(id, title,content);

    }
    @RequestMapping("/inquiryCheck")
    public List<INQUIRIES> inquiryCheck(@RequestBody Map<String, String> requestData) {
        String id = requestData.get("id");
        System.out.println(id);

        List<INQUIRIES> inquiryCheck = mapper.inquiryCheck(id);

        return inquiryCheck;

    }

    @RequestMapping("/nicksetting")
    public void nicksetting(@RequestBody Map<String, String> requestData) {
        String id = requestData.get("id");
        String inputText = requestData.get("inputText");
        System.out.println(id);
        mapper.nicksetting(id, inputText);

    }


    @RequestMapping("/numbersetting")
    public void numbersetting(@RequestBody Map<String, String> requestData) {
        String id = requestData.get("id");
        String inputText = requestData.get("inputText");
        System.out.println(id);
        mapper.numbersetting(id, inputText);

    }

    @RequestMapping("/pwsetting")
    public void pwsetting(@RequestBody Map<String, String> requestData) {
        String id = requestData.get("id");
        String newPassword = requestData.get("newPassword");
        System.out.println(id);
        System.out.println(newPassword);
        mapper.pwsetting(id, newPassword);

    }

    @RequestMapping("/idcheck")
    public int idcheck(@RequestBody Map<String, String> requestData) {
        String id = requestData.get("id");
        System.out.println(id);
        int cnt1 = mapper.idcheck(id); 
        System.out.println("들어옴 ------------------"+cnt1);
        if(cnt1 ==0){
            System.out.println("성콩");
            return cnt1;
        }else{
            System.out.println("넌 못지나간다.");
            return cnt1;
        }
    }

    @RequestMapping("/SignUp")
    public void SignUp(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String id = requestData.get("id");
        String pw = requestData.get("pw");
       
        String name = requestData.get("name");
        String phone = requestData.get("phone");

        System.out.println(id + pw + name + phone);
        mapper.signup(id,pw,name,phone); 
        
        
    }


    @RequestMapping("/getuser")
    public List<User> getuser(@RequestBody Map<String, String> requestData){
        String id = requestData.get("id");
        String password = requestData.get("password");

        List<User> userInfo = mapper.getuser(id, password);

        if (userInfo != null  && !userInfo.isEmpty()) {
            return userInfo;
        } else { 
            
            return null;
        }
    }


    @RequestMapping("/notification")
    public List<Notification> noti(@RequestBody Map<String, String> requestData){
        System.out.println("항~~~~~~~~~~~~~~~~~~~~~~이");
        String id = requestData.get("id");

        List<Notification> noti = mapper.noti(id);

        return noti;
    }


    @RequestMapping("/getnotification")
    public Notification getnotification(@RequestBody Map<String, String> requestData){
        String id = requestData.get("id");

        Notification noti = mapper.getNotification(id);
        

        return noti;
    }


}
