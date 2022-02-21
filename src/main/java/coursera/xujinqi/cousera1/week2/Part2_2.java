package coursera.xujinqi.cousera1.week2;

/**
 * @author 许 劲淇
 * @date 2022/1/23 12:34
 */
public class Part2_2 {
    public int howMany(String stringA, String stringB) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            int curIndex = stringB.indexOf(stringA, startIndex);
            if (curIndex == -1) {
                break;
            }
            count++;
            startIndex = curIndex + stringA.length();
        }
        return count;
    }

    public void test() {
        System.out.println(howMany("aa", "aabcdabdcaaabca"));
    }

    public static void main(String[] args) {
        new Part2_2().test();
    }
}
