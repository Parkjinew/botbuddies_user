package com.smhrd.botbuddies.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Setter
public class User {
    @NonNull
	private String user_id;
    private String user_pw;
    private String user_name;
    private String user_nick;
    private String user_phone;
    private String user_role;
    private String state;
    private String location;
    private String joined_at;
    private String store_name;
    private String category_seq;
    private String AverageRating;
    private String ReviewCount;
    private String STORE_IMG;
    
    public User(String store_name, String category_seq, String averageRating, String reviewCount, String sTORE_IMG) {
        this.store_name = store_name;
        this.category_seq = category_seq;
        AverageRating = averageRating;
        ReviewCount = reviewCount;
        STORE_IMG = sTORE_IMG;
    }
}
