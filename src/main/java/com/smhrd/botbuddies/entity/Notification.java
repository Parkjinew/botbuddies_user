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
public class Notification{
    private int noti_seq;
    private String user_id;
    private String type;
    private String message;
    private String state;
    private String notice_at;
    
}