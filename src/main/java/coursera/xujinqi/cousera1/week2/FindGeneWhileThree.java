package coursera.xujinqi.cousera1.week2;

import edu.duke.StorageResource;

/**
 * @author 许 劲淇
 * @date 2022/1/23 11:20
 */
public class FindGeneWhileThree {
    public String findGene(String DNA, int where) {
        int startIndex = DNA.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(DNA, startIndex, "TAA");
        int tagIndex = findStopCodon(DNA, startIndex, "TAG");
        int tgaIndex = findStopCodon(DNA, startIndex, "TGA");
        // int minIndex = Math.min(taaIndex, tagIndex);
        // minIndex = Math.min(minIndex, tgaIndex);

        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }

        if (minIndex == -1) {
            return "";
        }
        return DNA.substring(startIndex, minIndex + 3);
    }

    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int curIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while (curIndex != -1) {
            int diff = curIndex - startIndex;
            if (diff % 3 == 0) {
                return curIndex;
            } else {
                curIndex = dnaStr.indexOf(stopCodon, curIndex + 1);
            }
        }
        return -1;
    }

    public void printAllGene(String DNA) {
        int startIndex = 0;
        int count = 0;

        while (true) {
            String curGene = findGene(DNA, startIndex);
            if (curGene.isEmpty()) {
                break;
            }
            count++;
            System.out.println(curGene);

            startIndex = DNA.indexOf(curGene, startIndex) + curGene.length();
        }
        System.out.println(count);
    }

    public StorageResource getAllGene(String DNA) {
        StorageResource geneList = new StorageResource();

        int startIndex = 0;

        while (true) {
            String curGene = findGene(DNA, startIndex);
            if (curGene.isEmpty()) {
                break;
            }
            geneList.add(curGene);

            startIndex = DNA.indexOf(curGene, startIndex) + curGene.length();
        }
        return geneList;
    }

    // public void testing() {
    // String DNA = "AATGCGTAATTAATCG";
    // System.out.println(DNA);
    // String gene = findGene(DNA);
    // System.out.println(gene);
    //
    // DNA = "CGATGGTTGATAAGCCTAAGCTATAA";
    // System.out.println(DNA);
    // gene = findGene(DNA);
    // System.out.println(gene);
    //
    // DNA = "CGATGGTTGATAAGCCTAAGCTAAA";
    // System.out.println(DNA);
    // gene = findGene(DNA);
    // System.out.println(gene);
    // }
    public void testOn(String DNA) {
        // printAllGene(DNA);

        StorageResource genes = getAllGene(DNA);
        for (String s : genes.data()) {
            System.out.println(s);
        }
    }

    public void test() {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        // testOn("AATGCTAACTAGCTGACTAAT");
    }

    public static void main(String[] args) {
        new FindGeneWhileThree().test();
    }
}
