package no.ingesen.martin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * sub
 *
 * @author Martin Ingesen
 * @since 19. November 2014
 */
public class SubtitleFinder {

    public static final String BASE_SEARCH_URL = "http://subscene.com/subtitles/release?q=";
    public static final String BASE_SUBTITLE_URL = "http://subscene.com";
    private static String title;

    public SubtitleFinder(String title) {
        this.title = title;
    }

    public URL getZipURL() throws IOException {
        // get all subtitle pages
        String url = getSubtitleURLs(title);

        Document doc = Jsoup.connect(BASE_SUBTITLE_URL + url).get();
        Elements link = doc.select(".button");
        String subtitle_url = link.get(0).toString().split("\"")[1];
        System.out.println("Found subtitle.");
        return new URL(subtitle_url);
    }

    private String getSubtitleURLs(String film_title) throws IOException {
        Document doc = Jsoup.connect(BASE_SEARCH_URL + film_title).cookie("LanguageFilter", "13").get();
        Elements links = doc.select("table .a1 a");
        String url = links.get(0).attr("href");
        System.out.println("Found subtitle-page: " + url);
        return url;
    }
}
