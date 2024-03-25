package com.smhrd.botbuddies.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ReviewImg {
    private int img_seq;
    private int review_seq;
    private String img_filename;
}
