package br.com.marceloazvedo;

import br.com.marceloazvedo.domain.Movie;
import br.com.marceloazvedo.mapper.MovieMapper;
import br.com.marceloazvedo.util.CSVReader;
import br.com.marceloazvedo.util.PathHelper;
import br.com.marceloazvedo.util.interfaces.ICSVReader;

public class ReadCSV {

    public static void main(String[] args) throws Exception {
        String linksCSVPath = PathHelper.getInstance().getFilePathFromResources("imdb-movies-info/movies.csv");
        ICSVReader<Movie> csvReader = new CSVReader<>(linksCSVPath, true);
        csvReader.prepareReader(MovieMapper.class);
    }

}
