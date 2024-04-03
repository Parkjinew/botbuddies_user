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
    private int people_num;
    private String create_at;
    private int count;

    public Tabling(int tabling_seq, int store_seq, String user_id, int wait_num, String state, int people_num,
            String create_at) {
        this.tabling_seq = tabling_seq;
        this.store_seq = store_seq;
        this.user_id = user_id;
        this.wait_num = wait_num;
        this.state = state;
        this.people_num = people_num;
        this.create_at = create_at;
    }

    

    
    
}
