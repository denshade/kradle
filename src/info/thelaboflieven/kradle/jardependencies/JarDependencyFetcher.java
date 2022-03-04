package info.thelaboflieven.kradle.jardependencies;

import info.thelaboflieven.kradle.TimingInfo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JarDependencyFetcher
{
    public static void main(String[] args) throws IOException {
        File f = new File("dependencies.txt");
        if (!f.exists()) {
            System.err.println("Couldn't find dependencies.txt file");
        } else {
            System.out.println("Found dependencies.txt");
        }
        readLinesAndDownload(f);
    }

    public static void readLinesAndDownload(File f) throws IOException {
        readLinesAndDownload(Files.readAllLines(f.toPath()));
    }

    public static List<TimingInfo> readLinesAndDownload(List<String> urls) {
        return readLinesAndDownload(urls, new JarDependencyOptions());
    }
    public static List<TimingInfo> readLinesAndDownload(List<String> urls, JarDependencyOptions options)  {
        List<TimingInfo> timingInfos = new ArrayList<>();
        Stream<String> lineStream;
        if (options.isParallel()) {
            lineStream = urls.stream();
        } else {
            lineStream = urls.parallelStream();
        }
        lineStream.forEach((line) -> {
            var timingInfo = new TimingInfo();
            long start = System.currentTimeMillis();
            String[] lineParts = line.split(",");
            try {
                URL url = new URL(lineParts[0]);
                File filename = new File(lineParts[1]);
                downloadURL(url, filename);
                long end = System.currentTimeMillis();
                timingInfo.timing = end - start;
                timingInfo.url = url;
                timingInfo.filepath = filename;
                timingInfo.status = "OK";
            } catch (MalformedURLException e) {
                timingInfo.status = e.getMessage();
            }
            timingInfos.add(timingInfo);
        });
        return timingInfos;
    }

    private static void downloadURL(URL urlPath, File filenamePath)
    {
        System.out.println("Storing URL " + urlPath + " to " + filenamePath.getAbsolutePath());
        try (BufferedInputStream in = new BufferedInputStream(urlPath.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filenamePath)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
