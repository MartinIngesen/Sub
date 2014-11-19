package no.ingesen.martin;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;

/**
 * sub
 *
 * @author Martin Ingesen
 * @since 19. November 2014
 */
public class Zip {

    private File file;


    public Zip(File file) {
        this.file = file;
    }

    public File extract() {
        File file = null;
        try {
            ZipFile zipFile = new ZipFile(this.file);
            zipFile.extractAll("./");
            file = zipFile.getFile();
        } catch (ZipException e) {
            System.err.println("Failed to extract archive: " + e.getMessage());
        }
        return file;
    }

    public boolean delete() {
        return file.delete();
    }
}
