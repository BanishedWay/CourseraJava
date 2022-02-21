package coursera.xujinqi.cousera1.week2;

/**
 * @author 许 劲淇
 * @date 2022/1/23 10:04
 */
public class Part2 {
    public String findGeneSimple(String DNA, int startCodon, int stopCodon) {
        String res = "";
        String subDNA = DNA.substring(startCodon, stopCodon);

        int startIndex = subDNA.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = subDNA.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1) {
            return "";
        }
        res = DNA.substring(startIndex, stopIndex + 3);
        if (res.length() == 0 || res.length() % 3 != 0) {
            res = "";
        }

        return res;
    }

    public void testFindGeneSimple() {
        String DNA = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA strand is " + DNA);
        String res = findGeneSimple(DNA, 0, 13);
        System.out.println("Gene is " + res);
        System.out.println(res.toLowerCase());
    }

    public static void main(String[] args) {
        new Part2().testFindGeneSimple();
    }
}
