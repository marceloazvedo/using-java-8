package br.com.marceloazvedo.util;

import java.io.File;
import java.io.FileNotFoundException;

public class PathHelper {

    private static final String RESOURCES_PROJECT_PATH = "/src/main/resources/";

    private static PathHelper instance = null;

    private PathHelper() {
    }

    public static PathHelper getInstance() {
        if (instance == null) {
            instance = new PathHelper();
        }
        return instance;
    }

    public String getFilePathFromResources(String fileInResources) throws FileNotFoundException {
        File projectPath = new File("");
        String projectAbsolutePath = projectPath.getAbsolutePath();
        String resourcesProjectPath = projectAbsolutePath.concat(RESOURCES_PROJECT_PATH);
        String resourcesFilePath = resourcesProjectPath.concat(fileInResources);
        File fFile = new File(resourcesFilePath);
        if (fFile.exists() == false)
            throw new FileNotFoundException(resourcesFilePath);
        return resourcesProjectPath.concat(fileInResources);
    }


}
