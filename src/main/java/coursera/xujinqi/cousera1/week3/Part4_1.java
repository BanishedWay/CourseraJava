package coursera.xujinqi.cousera1.week3;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

/**
 *
 * @author 许 劲淇
 * @date 2022-01-26 15:10
 */
public class Part4_1 {

    // quiz
    private String fileName = "data\\exports\\exportdata.csv";
    // test file simple
    // private String fileName = "data\\exports\\exports_small.csv";
    // simple test file small
    // private String fileName = "data\\exports\\exports_small_Ms.csv";

    public void tester() {
        File f = new File(fileName);
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        // System.out.println(countryInfo(parser, "Nauru"));

        // listExportsTwoProducts(parser, "cotton", "flowers");

        // System.out.println(numberOfExporters(parser, "cocoa"));

        bigExporters(parser, "$999,999,999,999");
    }

    /**
     * 从CSV文件中读取该国产出和产值
     * 
     * @param parser
     * @param country
     * @return String res 返回国家产出和产值
     */
    public String countryInfo(CSVParser parser, String country) {
        String res = "NOT FOUND";
        for (CSVRecord record : parser) {
            String name = record.get("Country");
            if (country.equals(name)) {
                res = country + " " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return res;
    }

    /**
     * 打印同时产出两种资源的国家
     * 
     * @param parser
     * @param exportItem1
     * @param exportItem2
     */
    public void listExportsTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    /**
     * 返回产出特定资源的国家数
     * 
     * @param parser
     * @param exportItem
     * @return int count 返回国家数
     */
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 输出产值大于一定数值的国家
     * 
     * @param parser
     * @param amount
     */
    public void bigExporters(CSVParser parser, String amount) {
        // 首先截取数字部分

        double amountNumber = translateMoneyToDouble(amount);

        for (CSVRecord record : parser) {
            String outcome = record.get("Value (dollars)");
            if (translateMoneyToDouble(outcome) > amountNumber) {
                System.out.println(record.get("Country") + " " + outcome);
            }
        }
    }

    /**
     * 货币转化
     * 将货币转化为数值
     * 
     * @param money
     * @return
     */
    public double translateMoneyToDouble(String money) {
        String amount = "";
        for (int i = 1; i < money.length(); i++) {
            if (money.charAt(i) == ',') {
                continue;
            }
            amount += money.charAt(i);
        }
        return Double.parseDouble(amount);
    }

    public static void main(String[] args) {
        new Part4_1().tester();
    }
}
