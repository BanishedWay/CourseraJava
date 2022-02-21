package coursera.xujinqi.cousera1.week3;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffe");
    }

    public static void main(String[] args) {
        new WhichCountriesExport().whoExportCoffee();
    }
}
