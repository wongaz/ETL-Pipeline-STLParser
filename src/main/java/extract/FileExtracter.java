package extract;

import lombok.Data;

import java.util.Map;

@Data
public class FileExtracter extends AbstractExtracter{

    private String relativeFileDirectory;

    @Override
    public void setExtractionMap(Map<String, String> extractionMap) {



    }

    @Override
    public boolean read() {
        return false;
    }
}
