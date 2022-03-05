package info.thelaboflieven.kradle.buildclasses;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class JavaSourceFileFinderTest
{
    @Test
    void recursesThroughDirectories()
    {
        assertNotNull(JavaSourceFileFinder.findJavaSources(new File("src")));
        assertTrue(JavaSourceFileFinder.findJavaSources(new File("src")).get(0).getAbsolutePath().contains(".java"));
    }
}