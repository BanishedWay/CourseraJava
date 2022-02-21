package coursera.xujinqi.cousera1.week1;

import edu.duke.FileResource;

/**
 * @author 许 劲淇
 * @date 2022/1/20 10:28
 */
public class Something {
    public static void main(String[] args) {
        int count = 0;
        FileResource file = new FileResource("words.txt");
        for (String word : file.lines()) {
            System.out.println(word);
            System.out.println(word);
            count++;
        }
        System.out.println(count);
    }
}
