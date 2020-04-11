package br.com.marceloazvedo.util.interfaces;

import br.com.marceloazvedo.exception.CSVReaderException;
import br.com.marceloazvedo.mapper.generic.IGenericMapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ICSVReader<E> {

    void prepareReader(Class<? extends IGenericMapper<E>> genericMapper) throws CSVReaderException, IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    String[] getTitle();

    List<E> getData() throws Exception;

}
