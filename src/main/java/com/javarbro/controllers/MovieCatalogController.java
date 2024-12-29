package com.javarbro.controllers;

import com.javarbro.dto.MovieCatalog;
import com.javarbro.dto.MovieInfo;
import com.javarbro.dto.MovieRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("catalog")
public class MovieCatalogController {

    @Autowired
    @Qualifier("movieInfoRestClient")
    private RestClient movieInfoRestClient;

    @Autowired
    @Qualifier("movieRatingsRestClient")
    private RestClient movieRatingsRestClient;

    @Autowired
    @Qualifier("restClient")
    private RestClient restClient;

    public static List<MovieCatalog> movieCatalogs = Arrays.asList(
            new MovieCatalog("shashi", 100),
            new MovieCatalog("raj", 200),
            new MovieCatalog("deepthi", 300),
            new MovieCatalog("viru", 400),
            new MovieCatalog("deepika", 500),
            new MovieCatalog("shashi", 500),
            new MovieCatalog("raj", 300),
            new MovieCatalog("viru", 200),
            new MovieCatalog("deepthi", 100),
            new MovieCatalog("deepika", 100),
            new MovieCatalog("bhuvana", 400),
            new MovieCatalog("deepika", 300)
    );

    @GetMapping("{userId}")
    public List<MovieCatalog> getMovieCatalog(@PathVariable String userId) {
        return movieCatalogs.stream().filter(movieCatalog -> movieCatalog.getUserId().equals(userId))
                .map(movieCatalog -> {
                    //below is my service
                    /*Map<String, Object> uriVariablesMap = new HashMap<>();
                    uriVariablesMap.put("movieId", movieCatalog.getMovieId());
                    MovieInfo movieInfo = movieInfoRestClient.get().uri("/movies/{movieId}", uriVariablesMap).retrieve().body(new ParameterizedTypeReference<MovieInfo>() {});*/

                    //below is external call to IMDB to get movie information
                    MovieInfo movieInfo = restClient.get().uri("https://api.themoviedb.org/3/movie/"+movieCatalog.getMovieId()+"?api_key=8520bf3a5e1e928b42f0b61e4818982b").retrieve().body(new ParameterizedTypeReference<MovieInfo>() {
                    });

                    Map<String, Object> uriVariablesMap2 = new HashMap<>();
                    uriVariablesMap2.put("userId", userId);
                    uriVariablesMap2.put("movieId", movieCatalog.getMovieId());
                    MovieRatings movieRatings = movieRatingsRestClient.get().uri("/movieRatings/{userId}/{movieId}", uriVariablesMap2).retrieve().body(new ParameterizedTypeReference<MovieRatings>() {
                    });

                    if(movieInfo!=null) {
                        movieInfo.setMovieId(movieCatalog.getMovieId());
                        movieInfo.setDescription(movieInfo.getOverview());
                        movieInfo.setName(movieInfo.getOriginal_title());
                    }
                    movieCatalog.setMovieInfo(movieInfo);
                    movieCatalog.setMovieRatings(movieRatings);
                    return movieCatalog;
                }).toList();
    }
}