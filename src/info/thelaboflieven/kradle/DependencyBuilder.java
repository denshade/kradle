package info.thelaboflieven.kradle;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

public class DependencyBuilder
{
    public static void main(String[] args) throws IOException {
        File f = new File("dependencies.txt");
        if (!f.exists()) {
            System.err.println("Couldn't find dependencies.txt file");
        } else {
            System.out.println("Found dependencies.txt");
        }
        List<String> urls = Files.readAllLines(f.toPath());
        for (String line : urls)
        {
            String[] lineParts = line.split(",");
            downloadURL(lineParts[0], lineParts[1]);
        }


    }

    private static void downloadURL(String urlPath, String filenamePath)
    {
        try (BufferedInputStream in = new BufferedInputStream(new URL(urlPath).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filenamePath)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
        }
    }
}
