package br.com.marceloazvedo.mapper.generic;

import java.lang.reflect.InvocationTargetException;

public interface IGenericMapper<T> {

    T map(String[] t) throws Exception;

}
