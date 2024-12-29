package com.javarbro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieInfo {
    private Integer movieId;
    private String name;
    private String description;
    private String release_date;
    private String original_language;
    private String original_title;
    private String overview;

}
