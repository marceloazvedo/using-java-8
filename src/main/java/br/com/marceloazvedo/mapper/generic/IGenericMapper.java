package br.com.marceloazvedo.mapper.generic;

import br.com.marceloazvedo.exception.MapperException;

public interface IGenericMapper<T> {

    T map(String[] t) throws MapperException;

}
