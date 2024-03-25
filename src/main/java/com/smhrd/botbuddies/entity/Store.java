package com.smhrd.botbuddies.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Store {


    private int store_seq;
    private String user_id;

    private String store_name;
    private String store_addr;
    private String store_phone;

    private int category_seq;
    private String store_desc;
    private String tabling_state;
    private String state;
    private String open_time;
    private String end_time;
    private String open_state;

    private int AverageRating; 

    private int ReviewCount;
   
    private String ImageFilename;

    private int tableCount;

    public Store(int store_seq, String store_name, int category_seq, int AverageRating, int ReviewCount,String ImageFilename){
        this.store_seq = store_seq;
        this.store_name = store_name;
        this.category_seq = category_seq;
        this.AverageRating = AverageRating;
        this.ReviewCount = ReviewCount;
        this.ImageFilename = ImageFilename;
    }

    public Store(int store_seq, String store_name, int category_seq,  int averageRating,
            int reviewCount, String store_desc, String imageFilename) {
        this.store_seq = store_seq;
        this.store_name = store_name;
        this.category_seq = category_seq;
        this.store_desc = store_desc;
        AverageRating = averageRating;
        ReviewCount = reviewCount;
        ImageFilename = imageFilename;
    }

    public Store(String store_name) {
        this.store_name = store_name;
    }

    
    




    



    

    
    
}
