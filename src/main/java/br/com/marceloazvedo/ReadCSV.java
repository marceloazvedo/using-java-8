package br.com.marceloazvedo;

import br.com.marceloazvedo.exception.CSVReaderException;
import br.com.marceloazvedo.exception.MapperException;
import br.com.marceloazvedo.mapper.MovieMapper;
import br.com.marceloazvedo.mapper.RatingMapper;
import br.com.marceloazvedo.model.Movie;
import br.com.marceloazvedo.model.MovieRating;
import br.com.marceloazvedo.util.CSVReader;
import br.com.marceloazvedo.util.PathHelper;
import br.com.marceloazvedo.util.interfaces.ICSVReader;

import java.io.IOException;
import java.util.List;

public class ReadCSV {

    public static void main(String[] args) throws IOException, MapperException, CSVReaderException {
        String linksCSVPath = PathHelper.getInstance().getFilePathFromResources("imdb-movies-info/movies.csv");
        String ratingCSVPath = PathHelper.getInstance().getFilePathFromResources("imdb-movies-info/ratings.csv");
        ICSVReader<Movie> movieReader = new CSVReader<>(linksCSVPath, true);
        movieReader.prepareReader(MovieMapper.class);

        ICSVReader<MovieRating> ratingReader = new CSVReader<>(ratingCSVPath, true);
        ratingReader.prepareReader(RatingMapper.class);
        List<MovieRating> list = ratingReader.getData();
        System.out.println(list.get(800));

        /**
         * TODO: It's time to show man!!!
         * Get all movies with rating more than 4 and the gender Adventure and Children, separatedes.
         * Put all this movies in the adapter called GenreMovies, ex.:
         *      Adventure and the list of movies with rating more than 4;
         *      Children and the list of movies with rating...
         */
    }

}
