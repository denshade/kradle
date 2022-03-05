package info.thelaboflieven.kradle.buildclasses;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JavaSourceFileFinder
{
    public static List<File> findJavaSources(File sourceDirectory)
    {
        List<File> files = new ArrayList<>();
        for (File file : Objects.requireNonNull(sourceDirectory.listFiles()))
        {
            if (file.isDirectory()) {
                files.addAll(findJavaSources(file));
            }
            if (file.getAbsolutePath().endsWith(".java")){
                files.add(file);
            }
        }
        return files;
    }
}
