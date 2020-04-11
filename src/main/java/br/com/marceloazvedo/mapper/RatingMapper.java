package br.com.marceloazvedo.mapper;

import br.com.marceloazvedo.mapper.generic.GenericMapper;
import br.com.marceloazvedo.model.MovieRating;

import java.util.List;

public class RatingMapper extends GenericMapper<MovieRating> {

    public RatingMapper(List<String> fields) {
        super(fields);
    }
}
