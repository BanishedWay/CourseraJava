package coursera.xujinqi.cousera1.week2;

import edu.duke.FileResource;
import edu.duke.StorageResource;

/**
 * @author 许 劲淇
 * @date 2022/1/23 18:57
 */
public class Part3_3 {
    public void processGenes(StorageResource sr) {
        StorageResource moreThanNine = new StorageResource();
        StorageResource moreThanCG = new StorageResource();
        String maxString = "";

        for (String s : sr.data()) {
            if (s.length() > 60) {
                moreThanNine.add(s);
            }
            if (new Part3_2().cgRatio(s) > 0.35) {
                moreThanCG.add(s);
            }
            maxString = maxString.length() > s.length() ? maxString : s;
        }

        for (String s : moreThanNine.data()) {
            System.out.println(s);
        }
        System.out.print("超过60个字符的字符串长度：");
        System.out.println(moreThanNine.size());

        for (String s : moreThanCG.data()) {
            System.out.println(s);
        }
        System.out.print("高于0.35的字符串长度：");
        System.out.println(moreThanCG.size());

        System.out.println("最长字符串：");
        System.out.println(maxString);
        System.out.println(maxString.length());
    }

    public void testProcessGenes() {
        FileResource fr = new FileResource("brca1line.fa");
        String DNA = fr.asString().toUpperCase();
        // System.out.println(DNA.length());
        StorageResource genes = new FindGeneWhileThree().getAllGene(DNA);
        System.out.println(genes.size());
        processGenes(genes);
    }

    public static void main(String[] args) {
        new Part3_3().testProcessGenes();
    }
}
