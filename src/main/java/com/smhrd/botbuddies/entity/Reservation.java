package com.smhrd.botbuddies.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Reservation {

    private int reserve_seq;
    private String user_id;
    private int store_seq;
    private String reserve_name;
    private String reserve_date;
    private String reserve_time;
    private int reserve_num;
    private String state;
    private String reserve_at;

    @JsonCreator
    public Reservation(String user_id, int store_seq, String reserve_name, String reserve_date, String reserve_time,
            int reserve_num) {
        this.user_id = user_id;
        this.store_seq = store_seq;
        this.reserve_name = reserve_name;
        this.reserve_date = reserve_date;
        this.reserve_time = reserve_time;
        this.reserve_num = reserve_num;
    }


    

    
    
}
