package coursera.xujinqi.cousera1.week4;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class Assignment4 {

    private String path1 = "data\\us_babynames\\us_babynames_test\\yob";
    private String path2 = "data\\us_babynames\\us_babynames_by_year\\yob";
    private String path3 = "data\\us_babynames\\us_babynames_by_decade\\yob";

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
        // String fileName = "data\\us_babynames\\us_babynames_test\\example-small.csv";
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public int getRank(FileResource fr, String name, String gender) {
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

    public HashMap<Integer, List<String>> getRankInFile(FileResource fr, int year, String gender) {
        HashMap<Integer, List<String>> rankMap = new HashMap();
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                int rank = getRank(fr, record.get(0), gender);
                if (rankMap.containsKey(rank)) {
                    rankMap.get(rank).add(record.get(0));
                } else {
                    List<String> ranks = new ArrayList<>();
                    ranks.add(record.get(0));
                    rankMap.put(rank, ranks);
                }
            }
        }
        return rankMap;

    }

    public void testHashMap() {
        String fileName = path1 + 2012 + "short.csv";
        System.out.println(getRankInFile(new FileResource(fileName), 2012, "F"));
    }

    public void testRank() {
        String fileName = path2 + 1971 + ".csv";
        System.out.println(fileName);
        FileResource fr = new FileResource(fileName);

        int rank = getRank(fr, "Frank", "M");
        int trueRank = getTrueRank(fr, 1951, rank, "Frank", "M");
        System.out.println(trueRank);
        if (rank != -1) {
            System.out.println("2012 Mason in Male is " + rank);
        } else {
            System.out.println("No Mathch Name");
        }
    }

    public int getTrueRank(FileResource fr, int year, int rank, String name, String gender) {
        String name1 = getName(fr, year, rank, gender);
        if (name1.equals(name)) {
            return rank;
        } else {
            HashMap<Integer, List<String>> ranks = getRankInFile(fr, year, gender);

            int count = 0;
            for (String s : ranks.get(rank)) {
                if (s.equals(name)) {
                    break;
                }
                count++;
            }
            return count + rank;
        }
    }

    public String getName(FileResource fr, int year, int rank, String gender) {
        String res = "No Name";
        HashMap<Integer, List<String>> ranks = getRankInFile(fr, year, gender);

        int count = 0;
        while (!ranks.containsKey(rank) && rank > 0) {
            rank--;
            count++;
            // System.out.println(rank);
        }
        if (rank != 0) {
            List<String> names = ranks.get(rank);
            res = names.get(count);
        }
        return res;
    }

    public void testGetName() {
        String fileName = path2 + 1982 + ".csv";
        System.out.println(fileName);

        System.out.println(getName(new FileResource(fileName), 1982, 450, "M"));
    }

    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
        FileResource fr1 = new FileResource(path2 + year + ".csv");
        int rank1 = getRank(fr1, name, gender);
        int trueRank = getTrueRank(fr1, year, rank1, name, gender);
        // System.out.println(rank1);

        String newName = getName(new FileResource(path2 + newYear + ".csv"), newYear, trueRank, gender);
        if (newName.equals("No Name")) {
            return "";
        }

        System.out.println(name + " norn in " + year + " would be " +
                newName + " if she was born in " + newYear);
        return newName;
    }

    public void testNewName() {
        whatIsNameInYear("Susan", 1972, 2014, "F");
    }

    public int yearOfHighestRank(String name, String gender) {
        int rank = -1;
        String year = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int rank1 = getRank(fr, name, gender);
            System.out.println(rank1);
            if (rank == -1 && rank1 != -1) {
                rank = rank1;
                year = f.getName().substring(f.getName().lastIndexOf(".csv") - 4,
                        f.getName().lastIndexOf(".csv"));
                continue;
            }
            if (rank != -1 && rank1 != -1 && rank > rank1) {
                rank = rank1;
            }
        }
        // System.out.println(year);
        if (year.equals("")) {
            return -1;
        }
        // System.out.println(rank);
        return Integer.parseInt(year);
    }

    public void testYearOfRank() {
        System.out.println(yearOfHighestRank("Mich", "M"));
    }

    public double getAverageRank(String name, String gender) {
        int rank = 0;
        int totalRank = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            rank = getRank(fr, name, gender);
            if (rank != -1) {
                totalRank += rank;
                count++;
            }
        }
        if (count == 0) {
            return -1;
        }
        return (double) totalRank / count;
    }

    public void testGetAverageRank() {
        System.out.println(getAverageRank("Robert", "M"));
    }

    public int getTotalBirthsRankedHigher(FileResource fr, int year, String name, String gender) {
        int sum = 0;
        int rank = getRank(fr, name, gender);
        int trueRank = getTrueRank(fr, year, rank, name, gender);

        for (int i = 1; i < trueRank; i++) {
            String higherName = getName(fr, year, i, gender);
            for (CSVRecord record : fr.getCSVParser(false)) {
                if (record.get(0).equals(higherName)) {
                    // System.out.println(record.get(0));
                    sum += Integer.parseInt(record.get(2));
                }
            }
        }
        return sum;
    }

    public void testGetTotalRank() {
        String fileName = path2 + 1990 + ".csv";
        System.out.println(getTotalBirthsRankedHigher(new FileResource(fileName),
                2012, "Emily", "F"));
    }

    public static void main(String[] args) {
        new Assignment4().testGetTotalRank();
    }
}
