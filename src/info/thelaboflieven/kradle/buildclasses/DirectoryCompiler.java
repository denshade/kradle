package info.thelaboflieven.kradle.buildclasses;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryCompiler
{

    public static void main(String[] args) throws IOException, InterruptedException {
        compile(new File("src"), new File("c:\\temp\\classes"), "javac.exe");
    }
    public static void compile(File source, File classOutputDirectory, String javacPath) throws IOException, InterruptedException {
        List<String> commandLine = JavaSourceFileFinder.findJavaSources(source).stream().map(File::getAbsolutePath).collect(Collectors.toList());
        commandLine.add(0, javacPath);
        commandLine.add("-d");
        commandLine.add(classOutputDirectory.getAbsolutePath());

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
