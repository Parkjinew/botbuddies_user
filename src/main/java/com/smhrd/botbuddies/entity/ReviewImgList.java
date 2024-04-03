package com.smhrd.botbuddies.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ReviewImgList {

    private Review review;
    private List<ReviewImg> reviewImg;
    private String user_nick;

    
    
    public ReviewImgList(Review review, List<ReviewImg> reviewImg) {
        this.review = review;
        this.reviewImg = reviewImg;
    }



    public ReviewImgList(Review review) {
        this.review = review;
    }

    
    
}


