package br.com.marceloazvedo.mapper;

import br.com.marceloazvedo.model.Movie;
import br.com.marceloazvedo.mapper.generic.GenericMapper;

import java.util.List;

public class MovieMapper extends GenericMapper<Movie> {

    public MovieMapper(List<String> fields) {
        super(fields);
        String[] f = new String[fields.size()];
    }

}
