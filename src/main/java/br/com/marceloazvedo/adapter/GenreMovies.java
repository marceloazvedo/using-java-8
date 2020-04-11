package br.com.marceloazvedo.adapter;

import br.com.marceloazvedo.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class GenreMovies {

    private String genre;
    private List<Movie> movies;

    public GenreMovies(String genre) {
        this.genre = genre;
        this.movies = new ArrayList<>();
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "GenreMovies{" +
                "genre='" + genre + '\'' +
                ", movies=" + movies +
                '}';
    }
}
