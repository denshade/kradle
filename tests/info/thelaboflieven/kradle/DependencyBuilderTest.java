package info.thelaboflieven.kradle;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DependencyBuilderTest {

    private static final String COMMONS_MATH3_URL = "https://repo1.maven.org/maven2/org/apache/commons/commons-math3/3.6.1/commons-math3-3.6.1.jar";
    private static final String SIMPLE_JSON_URL = "https://repo1.maven.org/maven2/com/googlecode/json-simple/json-simple/1.1.1/json-simple-1.1.1.jar";
    private static final String JSON_SIMPLE_JAR_FILE = "json-simple.jar";
    private static final String COMMONS_JAR_FILE = "commons.jar";

    @Test
    void parallelDownloads()
    {
        File jsonSimpleJar = new File(JSON_SIMPLE_JAR_FILE);
        File commonJar = new File(COMMONS_JAR_FILE);
        JarDependencyFetcher.readLinesAndDownload(List.of(COMMONS_MATH3_URL + "," +commonJar,
                SIMPLE_JSON_URL + "," +jsonSimpleJar));
        assertTrue(jsonSimpleJar.exists());
        assertTrue(commonJar.exists());
        jsonSimpleJar.delete();
        commonJar.delete();
    }

    @Test
    void downloadInfo()
    {
        File jsonSimpleJar = new File(JSON_SIMPLE_JAR_FILE);
        File commonJar = new File(COMMONS_JAR_FILE);
        var details = JarDependencyFetcher.readLinesAndDownload(List.of(COMMONS_MATH3_URL + "," +commonJar,
                SIMPLE_JSON_URL + "," +jsonSimpleJar));
        assertNotNull(details);
        assertEquals(details.size(),2);
        jsonSimpleJar.delete();
        commonJar.delete();
    }

}