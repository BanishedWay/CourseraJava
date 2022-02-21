package coursera.xujinqi.cousera1.week4;

import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();

        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));

            if (numBorn <= 100) {
                System.out.println("Name " + record.get(0) +
                        " Gender " + record.get(1) +
                        " Num Born " + record.get(2));
            }
        }
    }

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int boyBirths = 0;
        int girlBirths = 0;
        int boyNames = 0;
        int girlNames = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            if (record.get(1).equals("M")) {
                boyBirths += numBorn;
                boyNames++;
            } else {
                girlBirths += numBorn;
                girlNames++;
            }
        }
        System.out.println("totalBirths = " + totalBirths);
        System.out.println("boyBirths = " + boyBirths);
        System.out.println("girlBirths = " + girlBirths);
        System.out.println("boyNames = " + boyNames);
        System.out.println("girlNames = " + girlNames);

    }

    public void test() {
        String fileName = "data\\us_babynames\\us_babynames_test\\example-small.csv";
        FileResource fr = new FileResource(fileName);
        totalBirths(fr);
    }

    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource();
        int rank = -1;
        int num = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                num = Integer.parseInt(record.get(2));
                rank = 1;
            }
        }
        if (rank == -1) {
            return -1;
        }

        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                if (num < Integer.parseInt(record.get(2))) {
                    rank++;
                }
            }
        }
        return rank;
    }

    public void testRank() {
        int rank = getRank(2012, "Mason", "F");
        if (rank != -1) {
            System.out.println("2012 Mason in Female is ");
        } else {
            System.out.println("No Mathch Name");
        }
    }

    public static void main(String[] args) {
        new BabyBirths().testRank();
    }
}
