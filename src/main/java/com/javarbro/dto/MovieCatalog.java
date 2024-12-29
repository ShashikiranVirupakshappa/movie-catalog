package com.javarbro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieCatalog {
    private String userId;
    private Integer movieId;
    private MovieInfo movieInfo;
    private MovieRatings movieRatings;

    public MovieCatalog(String userId, Integer movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }
}
