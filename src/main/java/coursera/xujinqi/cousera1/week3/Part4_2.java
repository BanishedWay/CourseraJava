package coursera.xujinqi.cousera1.week3;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

/**
 *
 * @author 许 劲淇
 * @date 2022-01-26 17:37
 */
public class Part4_2 {
    // 找到温度最低的一天

    /**
     * 找到一天中温度最低的一条记录
     * 
     * @param parser
     * @return
     */
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord record : parser) {
            coldestSoFar = getSmallestOfTwo(coldestSoFar, record, "TemperatureF");
        }
        return coldestSoFar;
    }

    /**
     * 根据温度返回最低温度的一条记录
     * 
     * @param record1
     * @param record2
     * @return
     */
    public CSVRecord getSmallestOfTwo(CSVRecord record1, CSVRecord record2, String str) {
        if (record1 == null) {
            record1 = record2;
        } else {
            String record1String = record1.get(str);
            String record2String = record2.get(str);
            if (!record1String.equals("N/A") && !record2String.equals("N/A")) {
                double recordTemp1 = Double.parseDouble(record1String);
                double recordTemp2 = Double.parseDouble(record2String);
                if (recordTemp1 > recordTemp2 && recordTemp1 != -9999 && recordTemp2 != -9999) {
                    record1 = record2;
                }
            }
        }
        return record1;
    }

    /**
     * 测试一天中最冷的温度和时间
     */
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldestSoFar = coldestHourInFile(fr.getCSVParser());
        System.out.println(coldestSoFar.get("TemperatureF") + " at " + coldestSoFar.get("TimeEDT"));
    }

    /**
     * 返回一年中最冷的那一天的绝对路径
     * 
     * @return
     */
    public String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        File f = null;

        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            if (f == null) {
                f = file;
            }
            // 调用方法找到一天中最冷的时刻代表那一天
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getSmallestOfTwo(coldestSoFar, current, "TemperatureF");

            // 取消注释将测量标准由温度变为湿度
            // CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            // coldestSoFar = getSmallestOfTwo(coldestSoFar, current, "Humidity");
            if (coldestSoFar == current) {
                f = file;
            }
        }
        // System.out.println(coldestSoFar.toString());
        return f.getAbsolutePath();
    }

    public void testFileWithColdestTemperature() {
        // 获取绝对路径
        String filePath = fileWithColdestTemperature();
        int index = filePath.lastIndexOf("\\");
        String fileName = filePath.substring(index + 1);

        // 根据文件的绝对路径创建文件对象
        // 获取相关属性
        FileResource fr = new FileResource(new File(filePath));
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestSoFar = coldestHourInFile(parser);

        // CSVRecord coldestSoFar = lowestHumidityInFile(parser);

        // 取消注释将输出湿度为衡量标准的结果
        // System.out.println("Coldest day was in file " + fileName);
        // System.out.println("Coldest temperature on that day was " +
        // coldestSoFar.get("Humidity"));
        // System.out.println("All the Temperatures on the coldest day were:");
        // for (CSVRecord record : fr.getCSVParser()) {
        // System.out.println(record.get("DateUTC") + " " + record.get("Humidity"));
        // }

        // 根据返回文件路径得到最冷的一天的记录对象
        // 输出预测结果
        System.out.println("Coldest day was in file " + fileName);
        System.out.println("Coldest temperature on that day was " +
                coldestSoFar.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord record : fr.getCSVParser()) {
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
    }

    // 找到最低湿度的一天，语法类似温度。可以增加一个String参数将二者合并
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumiditySoFar = null;
        for (CSVRecord record : parser) {
            lowestHumiditySoFar = getSmallestOfTwo(lowestHumiditySoFar, record, "Humidity");
        }
        return lowestHumiditySoFar;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println(
                "Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    /**
     * 得到一天中的平均温度
     * 可更改参数得到平均湿度
     * 
     * @param parser
     * @return
     */
    public double averageTemperatureInFile(CSVParser parser) {
        double averageTemperature = 0.0;
        int count = 0;
        for (CSVRecord record : parser) {
            count++;
            averageTemperature += Double.parseDouble(record.get("TemperatureF"));
        }
        return averageTemperature / count;
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }

    /**
     * 输出大于一定湿度的平均温度
     * 
     * @param parser
     * @param humidity
     * @return
     */
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int humidity) {
        double averageTemperature = 0.0;
        int count = 0;
        for (CSVRecord record : parser) {
            String humidityStr = record.get("Humidity");
            if (humidityStr.equals("N/A")) {
                continue;
            }
            int humidityInt = Integer.parseInt(humidityStr);
            if (humidityInt >= humidity) {
                count++;
                averageTemperature += Double.parseDouble(record.get("TemperatureF"));

            }
        }
        if (count == 0) {
            return 0.0;
        }
        return averageTemperature / count;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double res = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (res == 0.0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + res);
        }
    }

    public static void main(String[] args) {
        Part4_2 t = new Part4_2();
        // t.testColdestHourInFile();
        t.testFileWithColdestTemperature();
        // t.testLowestHumidityInFile();
        // t.testAverageTemperatureInFile();
        // t.testAverageTemperatureWithHighHumidityInFile();

    }
}
