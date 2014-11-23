import no.ingesen.martin.Zip;
import no.ingesen.martin.ZipDownloader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * sub
 *
 * @author Martin Ingesen
 * @since 23. November 2014
 */
public class TestZipDownloader {

    URL testURL;

    ZipDownloader zD;

    Zip zip;

    @Before
    public void setUp() throws Exception {
        zD = new ZipDownloader();
        try {
            testURL = new URL("http://subscene.com/subtitles/lucy/english/1016560");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThatItDownloadsAZipFile() throws Exception {
        File[] files = getFilesInFolder("./");

        int numOfZips = countZipFiles(files);

        zip = zD.download(testURL);

        File[] newFiles = getFilesInFolder("./");
        int newNumOfZips = countZipFiles(newFiles);
        assertNotEquals(numOfZips, newNumOfZips);

    }

    private int countZipFiles(File[] files) {
        int numOfZips = 0;
        for(File file : files){
            System.out.println(file.toString());
            if(file.toString().endsWith(".zip"))
                numOfZips++;
        }
        return numOfZips;
    }

    private File[] getFilesInFolder(String path) {
        File folder = new File(path);
        return folder.listFiles();
    }

    @After
    public void tearDown() throws Exception {
        if(zip != null)
            zip.delete();
    }
}
