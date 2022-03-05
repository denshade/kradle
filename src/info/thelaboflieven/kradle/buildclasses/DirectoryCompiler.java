package info.thelaboflieven.kradle.buildclasses;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryCompiler
{

    public static void main(String[] args) throws IOException, InterruptedException {
        compile(new File("src"));
    }
    public static void compile(File source) throws IOException, InterruptedException {
        List<String> commandLine = JavaSourceFileFinder.findJavaSources(source).stream().map(File::getAbsolutePath).collect(Collectors.toList());
        commandLine.add(0, "javac.exe");
        Process p = Runtime.getRuntime().exec(commandLine.toArray(new String[0]), new String[0], new File("classes"));
        p.waitFor();
        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));
        BufferedReader stdOut = new BufferedReader(new
                InputStreamReader(p.getInputStream()));
        System.out.println(stdError.lines().collect(Collectors.joining()));
        System.out.println(stdOut.lines().collect(Collectors.joining()));
    }
}
