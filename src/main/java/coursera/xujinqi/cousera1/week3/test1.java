package coursera.xujinqi.cousera1.week3;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

/**
 * @author 许 劲淇
 * @date 2022/1/26 12:40
 */
public class test1 {
    public void readFood() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // 根据读取的数据输入输出结果
        for (CSVRecord record : parser) {
            System.out.print(record.get("Name") + " ");
            System.out.print(record.get("Favorite Food") + " ");
            System.out.println(record.get("Favorite Color"));
        }
    }

    public static void main(String[] args) {
        // File f = new File("data\\brca1.fa");
        // FileResource fr = new FileResource(f);
        // for (String line : fr.lines()) {
        // System.out.println(line);
        // }
        new test1().readFood();
    }
}
