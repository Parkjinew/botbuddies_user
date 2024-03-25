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
    
    public ReviewImgList(Review review) {
        this.review = review;
    }

    
    
}


