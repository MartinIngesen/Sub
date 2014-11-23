package no.ingesen.martin;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * sub
 *
 * @author Martin Ingesen
 * @since 19. November 2014
 */
public class ZipDownloader{
    public Zip download(URL url){
        File destination = new File((int)(Math.random()*1001) + ".zip");
        try {
            FileUtils.copyURLToFile(url, destination);
        } catch (IOException e) {
            System.err.println("Failed to download archive: " + e.getMessage());
        }
        return new Zip(destination);
    }
}
