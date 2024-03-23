package com.smhrd.botbuddies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.botbuddies.entity.Order;
import com.smhrd.botbuddies.entity.User;

@Mapper
public interface UserMapper {

    public List<User> selectUser();

    public List<User> signin(String id,String password);

    public List<User> favorite(String id);

    public List<Order> orderlist(String id);

    public String storeSeq(String storeName);
    public void reviewWrite(String id,String storeSeq,int rating,String reviewTitle, String reviewText);
    public Integer reviewSeq(String id,String storeSeq,String reviewTitle,String reviewText);
    public void reviewImg(int review_seq, String photos);
}
