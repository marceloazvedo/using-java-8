package br.com.marceloazvedo.util;

import br.com.marceloazvedo.exception.CSVReaderException;
import br.com.marceloazvedo.exception.MapperException;
import br.com.marceloazvedo.mapper.generic.IGenericMapper;
import br.com.marceloazvedo.util.interfaces.ICSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader<E> implements ICSVReader<E> {

    private static final String CVS_COLUMN_SPLIT = ",";
    private static final String CSV_FILE_PATH_NOT_DEFINED_MESSAGE = "The CSV file path was not defined.";

    private String csvFilePath;
    private BufferedReader bufferedReader;
    private Boolean hasTitle;
    private String[] title;
    private List<E> data;
    private IGenericMapper<E> mapper;

    public CSVReader(String csvFilePath, Boolean hasTitle) {
        this.csvFilePath = csvFilePath;
        this.hasTitle = hasTitle;
    }

    public CSVReader(String csvFilePath) {
        this.csvFilePath = csvFilePath;
        this.hasTitle = false;
    }

    @Override
    public void prepareReader(Class<? extends IGenericMapper<E>> mapper) throws CSVReaderException, IOException, MapperException {
        if (csvFilePath == null)
            throw new CSVReaderException(CSV_FILE_PATH_NOT_DEFINED_MESSAGE);
        FileReader fileReader = new FileReader(csvFilePath);
        this.bufferedReader = new BufferedReader(fileReader);
        if (hasTitle)
            readTitleLine();
        try {
            this.mapper = mapper.getConstructor(List.class).newInstance(Arrays.asList(title));
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            throw new MapperException(e.getMessage(), mapper);
        }
    }

    private void readTitleLine() throws IOException {
        String titleLine = bufferedReader.readLine();
        this.title = titleLine.split(CVS_COLUMN_SPLIT);
    }

    @Override
    public String[] getTitle() {
        return title;
    }

    @Override
    public List<E> getData() throws IOException, MapperException {
        if (data == null)
            this.data = this.getDataFromCSVFile();
        return data;
    }


    private List<E> getDataFromCSVFile() throws MapperException, IOException {
        String line;
        List<E> data = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] lineData = line.split(CVS_COLUMN_SPLIT);
            data.add(mapper.map(lineData));
        }
        return data;
    }

}
