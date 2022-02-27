package info.thelaboflieven.kradle;

import java.io.File;

public class KradleLauncher
{
    public static void main(String[] args){
        File f = new File("build.gradle");
        if (!f.exists()) {
            System.err.println("Couldn't find build.gradle file");
        }
    }
}
