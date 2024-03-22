package com.smhrd.botbuddies.entity;

import java.util.List;
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
public class ReviewRequest {
    private String id;
    private String storeName;
    private int rating;
    private List<String> photos;
    private String reviewText;
    private String reviewTitle;
}
