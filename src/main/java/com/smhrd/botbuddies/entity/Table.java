package com.smhrd.botbuddies.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Table {

    private int table_seq;
    private int store_seq;
    private int table_num;
    private String table_state;
    public Table(int table_num) {
        this.table_num = table_num;
    }
    
    
    
    
}
