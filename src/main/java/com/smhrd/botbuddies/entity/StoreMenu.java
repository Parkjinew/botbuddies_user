package com.smhrd.botbuddies.entity;

import java.util.List;

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
public class StoreMenu {
    private Store store;
    private List<Menu> menu;
    private int store_seq;
    private String user_id;
    private List<Order> orders;

    
    

    @JsonCreator
    public StoreMenu(int store_seq, String user_id, List<Order> orders) {
        this.store_seq = store_seq;
        this.user_id = user_id;
        this.orders = orders;
    }




    public StoreMenu(Store store, List<Menu> menu) {
        this.store = store;
        this.menu = menu;
    }

    

    
    
}
