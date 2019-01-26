package extract;

import extract.parser.AbstractParser;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

@Data
public class FileExtractor extends AbstractExtractor {

    private String relativeFileDirectory;

    public FileExtractor() {

    }

    public FileExtractor(AbstractParser parser) {
        super(parser);
    }

    @Override
    public void setExtractionMap(String name, Map<String, String> extractionMap) {
        this.relativeFileDirectory = extractionMap.get("file");
        super.setNewModel(name);
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