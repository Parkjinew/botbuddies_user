package com.smhrd.botbuddies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.botbuddies.entity.INQUIRIES;
import com.smhrd.botbuddies.entity.Order;
import com.smhrd.botbuddies.entity.Reservation;
import com.smhrd.botbuddies.entity.ReviewMa;
import com.smhrd.botbuddies.entity.User;

@Mapper
public interface UserMapper {

    public List<User> selectUser();

    public List<User> signin(String id,String password);

    public List<User> favorite(String id);

    public List<Order> orderlist(String id);

    public String storeSeq(String storeName);
    public String userNick(String id);
    public void reviewWrite(String id,String storeSeq,int orderNum, int rating,String reviewTitle, String reviewText);
    public Integer reviewSeq(String nick,String storeSeq,String reviewTitle,String reviewText);
    public void reviewImg(int review_seq, String photos);
    public List<ReviewMa> reviewModify(String id);
    public void deleteImg(String review_seq);
    public void reviewDelete(String review_seq);
    public List<Reservation> reservaList(String id);
    public void reserveCancle(String id);
    public void userInquiry(String id, String title, String content);
    public List<INQUIRIES> inquiryCheck(String id);
    public void nicksetting(String id, String inputText);
    public void numbersetting(String id, String inputText);
    public void pwsetting(String id, String newPassword);
    public int idcheck(String id);
    public void signup(String id,String pw,String name,String phone );
}
