package load;

import model.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileOutLoader implements ILoader {

    private String fileName;

    @Override
    public boolean load(Model model) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            Map<String, ?> statsMap = model.getAnalysisMap();
            if (statsMap == null) {
                return false;
            }

            for (String str : statsMap.keySet()) {
                writer.write(str + ": " + statsMap.get(str));
                writer.write("\n");
            }

            writer.close();
            return true;
        } catch (IOException ex) {
            return false;
        }


    }

    /**
     * The incoming map needs to have the following
     * filename: <output_file>
     *
     * @param confMap
     */
    @Override
    public void setOutConfigurations(Map<String, String> confMap) {
        fileName = confMap.get("filename");
    }
}
