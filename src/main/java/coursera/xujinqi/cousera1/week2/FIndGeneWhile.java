package coursera.xujinqi.cousera1.week2;

/**
 * @author 许 劲淇
 * @date 2022/1/23 11:10
 */
public class FIndGeneWhile {
    public String findGene(String DNA, int where) {
        int startIndex = DNA.indexOf("ATG", where);
        int curIndex = DNA.indexOf("TAA", startIndex + 3);
        while (curIndex != -1) {
            if ((curIndex - startIndex) % 3 == 0) {
                return DNA.substring(startIndex, curIndex + 3);
            } else {
                curIndex = DNA.indexOf("TAA", curIndex + 1);
            }
        }
        return "";
    }

    public void printAllGene(String DNA) {
        int startIndex = 0;
        while (true) {
            String curGene = findGene(DNA, startIndex);
            if (curGene.isEmpty()) {
                break;
            }
            System.out.println(curGene);

            startIndex = DNA.indexOf(curGene, startIndex) + curGene.length();
        }
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
        printAllGene(DNA);
    }

    public void test() {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
    }

    public static void main(String[] args) {
        new FIndGeneWhile().test();
    }
}
