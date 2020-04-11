package br.com.marceloazvedo.mapper.generic;

import br.com.marceloazvedo.exception.MapperException;

import java.beans.Statement;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

public abstract class GenericMapper<T> implements IGenericMapper<T> {

    private static final String SET_PREFIX_METHOD = "set";

    protected Class<T> clazz;
    protected List<String> fields;

    public GenericMapper(List<String> fields) {
        this.fields = fields;
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T map(String[] data) throws MapperException {
        try {
            T t = (T) clazz.newInstance();
            for (int i = 0; i < fields.size(); i++) {
                setIn(t, fields.get(i), data[i]);
            }
            return t;
        }catch (MapperException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new MapperException(e.getMessage(), clazz);
        }
    }

    protected void setIn(T t, String field, String value) throws MapperException {
        try {
            String setMethodName = getSetMethodName(field);
            Object objectValue = getMethodValue(setMethodName, value);
            Statement statement = new Statement(t, setMethodName, new Object[]{objectValue});
            statement.execute();
        }catch (Exception e) {
            throw new MapperException(e.getMessage(), clazz);
        }
    }

    private String getSetMethodName(String attribute) {
        String firstCharacter = String.valueOf(attribute.charAt(0));
        attribute = attribute.replaceFirst(firstCharacter, firstCharacter.toUpperCase());
        return SET_PREFIX_METHOD.concat(attribute);
    }

    private Object getMethodValue(String methodName, String value) {
        Method[] methods = clazz.getMethods();
        Method methodFinded = Arrays.asList(methods).stream().filter((method ->
            methodName.equals(method.getName())
        )).findFirst().orElse(null);
        Class<?> parameterType = methodFinded.getParameterTypes()[0];
        if (Long.class.equals(parameterType)) {
            return Long.valueOf(value);
        } else if (Integer.class.equals(parameterType)) {
            return Integer.valueOf(value);
        } else if (Float.class.equals(parameterType)) {
            return Float.valueOf(value);
        }
        return value;
    }


}
