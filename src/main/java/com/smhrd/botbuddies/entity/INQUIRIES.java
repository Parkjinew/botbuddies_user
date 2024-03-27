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
public class INQUIRIES {
    
    private int inquiry_seq;
    private String user_id;
    private String title;
    private String details;
    private String answer;
    private String state;
    private String create_at;
    public INQUIRIES(String title, String details, String answer) {
        this.title = title;
        this.details = details;
        this.answer = answer;
    }
}
