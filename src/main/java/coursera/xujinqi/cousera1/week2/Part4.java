package coursera.xujinqi.cousera1.week2;

import edu.duke.URLResource;

/**
 * @author 许 劲淇
 * @date 2022/1/23 10:23
 */
public class Part4 {
    public static void main(String[] args) {
        URLResource file = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String item : file.words()) {
            System.out.println(item);
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if (pos != -1) {
                int beg = item.lastIndexOf("\"", pos);
                int end = item.indexOf("\"", pos + 1);
                System.out.println(item.substring(beg + 1, end));
            }
        }
    }

}
