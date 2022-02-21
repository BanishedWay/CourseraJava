package coursera.xujinqi.cousera1.week2;

/**
 * @author 许 劲淇
 * @date 2022/1/23 12:45
 */
public class FindABC {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            // System.out.println(index);

            if (index == -1) {
                break;
            }
            // System.out.println("index " + index);
            String found = input.substring(index + 1, index + 4);
            System.out.println(found);
            index = input.indexOf("abc", index + 3);

            // System.out.println("index after " + index);
            if (index >= input.length() - 3) {
                break;
            }
        }
    }

    public void test() {
        // findAbc("abcd");
        findAbc("abcabcabcabca");
    }

    public static void main(String[] args) {
        new FindABC().test();
    }
}
