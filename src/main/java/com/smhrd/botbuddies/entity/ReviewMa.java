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

public class ReviewMa {
    private String review_seq;
    private String user_id;
    private int store_seq;
    private String title;
    private String details;
    private String answer;
    private int score;
    private String store_name;
    private String image_filenames;
    private String review_date;
}
