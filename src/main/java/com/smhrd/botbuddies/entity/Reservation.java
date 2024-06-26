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
    private String store_name;
    private String store_user;

    




    public Reservation(int reserve_seq, String user_id, int store_seq, String reserve_name, String reserve_date,
            String reserve_time, int reserve_num, String state, String reserve_at) {
        this.reserve_seq = reserve_seq;
        this.user_id = user_id;
        this.store_seq = store_seq;
        this.reserve_name = reserve_name;
        this.reserve_date = reserve_date;
        this.reserve_time = reserve_time;
        this.reserve_num = reserve_num;
        this.state = state;
        this.reserve_at = reserve_at;
    }


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

    public Reservation(String user_id, int store_seq, String reserve_name, String reserve_date, String reserve_time,
            int reserve_num, String store_user) {
        this.user_id = user_id;
        this.store_seq = store_seq;
        this.reserve_name = reserve_name;
        this.reserve_date = reserve_date;
        this.reserve_time = reserve_time;
        this.reserve_num = reserve_num;
        this.store_user = store_user;
    }


    public Reservation(int reserve_seq, String reserve_name, String reserve_date, String reserve_time, int reserve_num, String state,
            String store_name, int store_seq) {
        this.reserve_seq = reserve_seq;
        this.reserve_name = reserve_name;
        this.reserve_date = reserve_date;
        this.reserve_time = reserve_time;
        this.reserve_num = reserve_num;
        this.state = state;
        this.store_name = store_name;
        this.store_seq = store_seq;
    }
    

    
    
}
