package br.com.marceloazvedo.function;

import br.com.marceloazvedo.adapter.GenreMovies;
import br.com.marceloazvedo.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class MoviesSelectors {
    public static Predicate<Movie> selectMovieHasGenre(String genreSelector) {
        return (movie) ->
                Arrays.asList(movie.getGenresAsArray()).contains(genreSelector)
        ;
    }

    public static BiFunction<ArrayList<GenreMovies>, Movie, ArrayList<GenreMovies>> selectAndSeparate(String... genres) {
        return (findeds, movie) -> {
            for(String genre : genres) {
                GenreMovies genreMovieToAdd = getGenreMoviesInListByGenre(genre, findeds);
                if(genreMovieToAdd == null) {
                    genreMovieToAdd = new GenreMovies(genre);
                    findeds.add(genreMovieToAdd);
                }
                if(Arrays.asList(movie.getGenresAsArray()).contains(genre)) {
                    genreMovieToAdd.getMovies().add(movie);
                }
            }
            return findeds;
        };
    }

    public static BinaryOperator<ArrayList<GenreMovies>> matchList() {
        return (arr1, arr2) -> {
            arr1.addAll(arr2);
            return arr1;
        };
    }

    public static GenreMovies getGenreMoviesInListByGenre(String genre, List<GenreMovies> list) {
        return list.stream()
                .filter(genreMovies -> genre.equals(genreMovies.getGenre()))
                .findFirst().orElse(null);
    }

}
