package coursera.xujinqi.cousera1.week3;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

/**
 *
 * @author 许 劲淇
 * @date 2022-01-26 16:48
 */
public class CSVMax {

    /**
     * 从单个文件中读取最大值
     * 
     * @param parser
     * @return
     */
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;

        for (CSVRecord record : parser) {
            largestSoFar = getLargestTwo(largestSoFar, record);
        }
        return largestSoFar;
    }

    public CSVRecord hottestInManyDays() {

        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestTwo(largestSoFar, current);
        }
        return largestSoFar;
    }

    public void testHottestHourInFile() {
        // FileResource fr = new FileResource();
        // CSVRecord record = hottestHourInFile(fr.getCSVParser());
        // System.out.println(record.get("TemperatureF") + " at " +
        // record.get("TimeEST"));
        CSVRecord record = hottestInManyDays();
        System.out.println(record.get("TemperatureF") + " at " + record.get("DateUTC"));
    }

    public CSVRecord getLargestTwo(CSVRecord largestSoFar, CSVRecord current) {
        if (largestSoFar == null) {
            largestSoFar = current;
        } else {
            double currentTemp = Double.parseDouble(current.get("TemperatureF"));
            double largest = Double.parseDouble(largestSoFar.get("TemperatureF"));

            largestSoFar = largest > currentTemp ? largestSoFar : current;
        }
        return largestSoFar;
    }

    public static void main(String[] args) {
        new CSVMax().testHottestHourInFile();
    }
}
