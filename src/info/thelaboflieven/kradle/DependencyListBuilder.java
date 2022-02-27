package info.thelaboflieven.kradle;

import java.io.File;

//From pom => dependencies.txt
public class DependencyListBuilder
{
    public static void main(String[] args){
        File f = new File("pom.list.txt");
        if (!f.exists()) {
            System.err.println("Couldn't find pom.list.txt file");
        }

    }
}
