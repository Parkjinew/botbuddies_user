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
public class Menu {

    private int menu_seq;
    private int store_seq;
    private String menu_name;
    private String menu_desc;
    private String menu_img;
    private int price;
    private String menu_state;
    private String create_at;
    
}
