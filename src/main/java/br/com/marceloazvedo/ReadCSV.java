package br.com.marceloazvedo;

import br.com.marceloazvedo.adapter.GenreMovies;
import br.com.marceloazvedo.exception.CSVReaderException;
import br.com.marceloazvedo.exception.MapperException;
import br.com.marceloazvedo.function.MoviesSelectors;
import br.com.marceloazvedo.mapper.MovieMapper;
import br.com.marceloazvedo.model.Movie;
import br.com.marceloazvedo.util.CSVReader;
import br.com.marceloazvedo.util.PathHelper;
import br.com.marceloazvedo.util.interfaces.ICSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {

    public static void main(String[] args) throws IOException, MapperException, CSVReaderException {
        String moviesCSVPath = PathHelper.getInstance().getFilePathFromResources("imdb-movies-info/movies.csv");
        ICSVReader<Movie> movieReader = new CSVReader<>(moviesCSVPath, true);
        movieReader.prepareReader(MovieMapper.class);
        List<Movie> movies = movieReader.getData();

        /**
         * Get all movies with rating more than 4 and the gender Adventure and Children, separated.
         * Put all this movies in the adapter called GenreMovies, ex.:
         *      Adventure and the list of movies with rating more than 4;
         *      Children and the list of movies with rating...
         */

        List<GenreMovies> moviesSeparatedByGenre = movies.stream()
                .reduce(new ArrayList<>(),
                        MoviesSelectors.selectAndSeparate("Adventure", "Children"),
                        MoviesSelectors.matchList());

        for(GenreMovies genreMovies : moviesSeparatedByGenre) {
            System.out.println("Genre: " + genreMovies.getGenre() + "\n");
            for(Movie movie: genreMovies.getMovies()) {
                System.out.println(movie);
            }
            System.out.println("\n");
        }

    }

}
