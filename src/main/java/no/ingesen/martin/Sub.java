package no.ingesen.martin;

import java.io.IOException;
import java.net.URL;

/**
 * sub
 *
 * @author Martin Ingesen
 * @since 04. November 2014
 */

public class Sub {


    public static void main(String[] args) throws IOException{
        if (args.length == 0) {
            System.out.println("No String as parameter.");
            System.out.println("TODO: Implement search for valid file.");
            return;
        }

        String film_title = args[0];

        System.out.printf("Searching for %s%n", film_title);

        SubtitleFinder subtitleFinder = new SubtitleFinder(film_title);
        URL url = subtitleFinder.getZipURL();

        ZipDownloader zipDownloader = new ZipDownloader();

        Zip zip = zipDownloader.download(url);
        zip.extract();
        zip.delete();

        System.out.println("Done. Have a nice day!");

    }
}

