package extract;

import extract.parser.AbstractParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class FileExtractor extends AbstractExtractor {

    private String relativeFileDirectory;

    public FileExtractor() {

    }

    public FileExtractor(AbstractParser parser) {
        super(parser);
    }

    /**
     * For the implementation of this Extractor in the configuration map for the Extractor
     * put
     * file: <file path">
     *
     * @param extractionMap
     */
    @Override
    public void setExtractionMap(Map<String, String> extractionMap) {
        this.relativeFileDirectory = extractionMap.get("file");
        if (this.relativeFileDirectory == null) {
            System.exit(1);
        }
    }

    @Override
    public boolean read() {

        try {
            BufferedReader b = new BufferedReader(new FileReader(new File(this.relativeFileDirectory)));
            String readLine;
            while ((readLine = b.readLine()) != null) {
                super.getParser().parse(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;


    }
}