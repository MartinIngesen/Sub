package no.ingesen.martin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * sub
 *
 * @author Martin Ingesen
 * @since 19. November 2014
 */
public class SubtitleFinder {

    public static final String BASE_SEARCH_URL = "http://subscene.com/subtitles/release?q=";
    public static final String BASE_SUBTITLE_URL = "http://subscene.com";
    private String title;
    private boolean showPicker = false;

    public SubtitleFinder(String title, ArrayList<String> flags) {
        this.title = title;

        for(String flag : flags){
            if(flag.equals("--pick")){
                showPicker = true;
            }
        }
    }

    public URL getZipURL() throws IOException {
        // get all subtitle pages
        String url = getSubtitleURLs(title);

        Document doc = Jsoup.connect(BASE_SUBTITLE_URL + url).get();
        Elements link = doc.select(".button");
        String subtitle_url = link.get(0).toString().split("\"")[1];
        return new URL(BASE_SUBTITLE_URL + subtitle_url);
    }

    private String getSubtitleURLs(String film_title) throws IOException {
        Document doc = Jsoup.connect(BASE_SEARCH_URL + film_title).cookie("LanguageFilter", "13").get();
        Elements links = doc.select("table .a1 a");

        int selectedSubtitle = 0;

        if(showPicker){
            for (int i = 0; i < 5; i++) {
                System.out.println(i+1 + " " + links.get(i).text());
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Select a number: ");
            selectedSubtitle = scanner.nextInt();
            scanner.nextLine();
            selectedSubtitle -= 1;
        }


        return links.get(selectedSubtitle).attr("href");
    }
}
