package coursera.xujinqi.cousera1.week4;

import java.io.File;

import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class Part5_1 {
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
        // String fileName = "data\\us_babynames\\us_babynames_test\\example-small.csv";
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public int getRank(FileResource fr, int year, String name, String gender) {
        int rank = 0;
        boolean flag = false;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                rank++;
                if (record.get(0).equals(name)) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            return rank;
        }
        return -1;
    }

    public void testRank() {
        FileResource fr = new FileResource();

        System.out.println(getRank(fr, 1971, "Frank", "M"));
    }

    public String getName(FileResource fr, int year, int rank, String gender) {
        int count = 0;
        String res = "NO NAME";
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                count++;
                if (count == rank) {
                    res = record.get(0);
                    break;
                }
            }
        }
        return res;
    }

    public void testGetName() {
        FileResource fr = new FileResource();

        System.out.println(getName(fr, 1982, 450, "M"));
    }

    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(new FileResource(), year, name, gender);
        return getName(new FileResource(), year, rank, gender);
    }

    public void testWhatIsNameInYear() {
        System.out.println(whatIsNameInYear("Owen", 1974, 2014, "M"));
    }

    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rank = -1;
        String year = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String year1 = f.getName().substring(f.getName().lastIndexOf(".csv") - 4,
                    f.getName().lastIndexOf(".csv"));
            int rank1 = getRank(fr, Integer.parseInt(year1), name, gender);
            if (rank == -1 && rank1 != -1) {
                rank = rank1;
                year = year1;
                continue;
            }
            if (rank != -1 && rank1 != -1 && rank > rank1) {
                rank = rank1;
                year = year1;
            }
        }
        return Integer.parseInt(year);
    }

    public void testYearOfRank() {
        System.out.println(yearOfHighestRank("Mich", "M"));
    }

    public double getAverageRank(String name, String gender) {
        double totalRank = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String year1 = f.getName().substring(f.getName().lastIndexOf(".csv") - 4,
                    f.getName().lastIndexOf(".csv"));
            int rank1 = getRank(fr, Integer.parseInt(year1), name, gender);
            if (rank1 != -1) {
                count++;
                totalRank += rank1;
            }
        }
        return totalRank / count;
    }

    public void testGetAverageRank() {
        System.out.println(getAverageRank("Robert", "M"));
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource();
        int rank = getRank(fr, year, name, gender);
        int total = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            int rank1 = getRank(fr, year, record.get(0), record.get(1));
            if (rank > rank1 && record.get(1).equals(gender) && !record.get(0).equals(name)) {
                // System.out.println(record.get(0));
                total += Integer.parseInt(record.get(2));
            }
        }
        return total;
    }

    public void testGetTotalBirthsRanked() {
        System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
    }

    public static void main(String[] args) {
        new Part5_1().testGetAverageRank();
    }

}
