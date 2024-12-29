package com.javarbro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieRatings {
    private String userId;
    private Integer movieId;
    private Integer rating;
}
