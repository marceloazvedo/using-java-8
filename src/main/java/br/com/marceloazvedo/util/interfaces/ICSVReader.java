package br.com.marceloazvedo.util.interfaces;

import br.com.marceloazvedo.exception.CSVReaderException;
import br.com.marceloazvedo.exception.MapperException;
import br.com.marceloazvedo.mapper.generic.IGenericMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ICSVReader<E> {

    void prepareReader(Class<? extends IGenericMapper<E>> genericMapper) throws CSVReaderException, IOException, MapperException;

    String[] getTitle();

    List<E> getData() throws IOException, MapperException;

}
