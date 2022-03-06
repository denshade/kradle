package info.thelaboflieven.kradle.buildclasses;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryCompilerTest {

    @Test
    void testCompile() throws IOException, InterruptedException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        File classes = new File(tmpdir+File.separator+"classes");
        classes.mkdirs();
        DirectoryCompiler.compile(new File("src"), classes, "javac.exe");
        var files = classes.listFiles();
        assertTrue(files.length > 0);
        classes.delete();
    }

}