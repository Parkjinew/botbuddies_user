package com.smhrd.botbuddies.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Tabling {
    private int tabling_seq;
    private int store_seq;
    private String user_id;
    private int wait_num;
    private String state;
    private String create_at;
    
}
