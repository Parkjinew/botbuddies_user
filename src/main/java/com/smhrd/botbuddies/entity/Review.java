package com.smhrd.botbuddies.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Review {
    private int review_seq;
    private String user_id;
    private int store_seq;
    private String title;
    private String details;
    private String answer;
    private String state;
    private int score;
    private String create_at;

    

}
