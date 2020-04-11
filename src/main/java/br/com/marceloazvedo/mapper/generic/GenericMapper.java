package br.com.marceloazvedo.mapper.generic;

import java.beans.Expression;
import java.beans.Statement;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

public abstract class GenericMapper<T> implements IGenericMapper<T> {

    protected Class<T> clazz;
    protected List<String> fields;

    public GenericMapper(List<String> fields) {
        this.fields = fields;
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T map(String[] data) throws Exception {
        T t = (T) clazz.newInstance();
        for (int i = 0; i < fields.size(); i++) {
            setIn(t, fields.get(i), data[i]);
        }
        return t;
    }

    protected void setIn(T t, String field, String value) throws Exception {
        String setMethodName = getSetMethodName(field);
        Object objectValue = getMethodValue(setMethodName, value);
        Statement statement = new Statement(t, setMethodName, new Object[]{objectValue});
        statement.execute();


        Expression expression = new Expression(t, getGetMethodName(field), new Object[0]);
        expression.execute();
    }

    private String getSetMethodName(String attribute) {
        String firstCharacter = String.valueOf(attribute.charAt(0));
        attribute = attribute.replaceFirst(firstCharacter, firstCharacter.toUpperCase());
        return "set".concat(attribute);
    }

    private String getGetMethodName(String attribute) {
        String firstCharacter = String.valueOf(attribute.charAt(0));
        attribute = attribute.replaceFirst(firstCharacter, firstCharacter.toUpperCase());
        return "get".concat(attribute);
    }

    private Object getMethodValue(String methodName, String value) {
        Method[] methods = clazz.getMethods();
        Method methodFined = Arrays.asList(methods).stream().filter((method ->
            methodName.equals(method.getName())
        )).findFirst().orElse(null);
        Class<?> parameterType = methodFined.getParameterTypes()[0];
        if (Long.class.equals(parameterType)) {
            return Long.valueOf(value);
        }
        return value;
    }


}
