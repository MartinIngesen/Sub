package no.ingesen.martin;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * sub
 *
 * @author Martin Ingesen
 * @since 04. November 2014
 */

public class Sub {

    public static String film_title;
    public static final String BASE_SEARCH_URL = "http://subscene.com/subtitles/release?q=";
    public static final String BASE_SUBTITLE_URL = "http://subscene.com";

    public static void main(String[] args) throws IOException, ZipException {
        if (args.length == 0) {
            System.out.println("No String as parameter.");
            System.out.println("TODO: Implement search for valid file.");
            return;
        }

        film_title = args[0];

        System.out.printf("Searching for %s%n", film_title);

        getSubtitle(film_title);

        System.out.println("Done. Have a nice day!");


    }

    private static void getSubtitle(String film_title) throws IOException, ZipException {
        String url = getSubtitlePageURL(film_title);
        String subtitleURL = getSubtitleURL(url);
        System.out.println("Downloading...");
        download(subtitleURL);
    }

    private static void download(String subtitleURL) throws IOException, ZipException {
        String zipName = film_title + ".zip";

        FileUtils.copyURLToFile(new URL(BASE_SUBTITLE_URL + subtitleURL), new File(zipName));
        System.out.println("Unzipping...");
        unZip(zipName);
    }

    private static void unZip(String zipName) throws ZipException {
        ZipFile zipFile = new ZipFile(zipName);

        zipFile.extractAll("./");
        File file = zipFile.getFile();
        file.delete();
    }

    private static String getSubtitleURL(String url) throws IOException {
        Document doc = Jsoup.connect(BASE_SUBTITLE_URL + url).get();
        Elements link = doc.select(".button");
        String subtitle_url = link.get(0).toString().split("\"")[1];
        System.out.println("Found subtitle.");
        return subtitle_url;
    }

    private static String getSubtitlePageURL(String film_title) throws IOException {
        Document doc = Jsoup.connect(BASE_SEARCH_URL + film_title).cookie("LanguageFilter", "13").get();
        Elements links = doc.select("table .a1 a");
        String url = links.get(0).toString().split("\"")[1];
        System.out.println("Found subtitle-page: " + url);
        return url;
    }
}

