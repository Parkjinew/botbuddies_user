package com.smhrd.botbuddies.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

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

public class Order {
    private int order_seq;
    private int store_seq;
    private String user_id;
    private int menu_seq;
    private int order_num;
    private int quantity;
    private int total_amount;
    private int discount_amount;
    private int pay_amount;
    private int paid_amount;
    private int pay_method;
    private String order_at;
    private String store_name;
    private String menu_names;
    private String image_filenames;
    private int review_seq;

    public Order(String store_name, int order_num, int total_amount, String menu_names, String image_filenames, int review_seq, String order_at) {
        this.order_num = order_num;
        this.total_amount = total_amount;
        this.store_name = store_name;
        this.menu_names = menu_names;
        this.image_filenames = image_filenames;
        this.review_seq = review_seq;
        this.order_at = order_at;
    }

    @JsonCreator
    public Order(int menu_seq, int quantity) {
        this.menu_seq = menu_seq;
        this.quantity = quantity;
    }

    
}
