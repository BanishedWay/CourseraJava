package coursera.xujinqi.cousera1.week2;

/**
 * @author 许 劲淇
 * @date 2022/1/23 10:06
 */
public class Part3 {
    public boolean twoOccurrences(String stringA, String stringB) {
        int firstTime = stringB.indexOf(stringA);
        int secondTime = stringB.lastIndexOf(stringA);
        return firstTime != secondTime;
    }

    public void testing() {
        String stringB = "banana";
        String stringA = "a";
        System.out.println("stringA is " + stringA + " and stringB is " + stringB);
        System.out.println(twoOccurrences(stringA, stringB));
        System.out.println(lastPart(stringA, stringB));

        stringA = "by";
        stringB = "Astory by Abby Long";
        System.out.println("stringA is " + stringA + " and stringB is " + stringB);
        System.out.println(twoOccurrences(stringA, stringB));
        System.out.println(lastPart(stringA, stringB));

        stringA = "a";
        stringB = "bbbba";
        System.out.println("stringA is " + stringA + " and stringB is " + stringB);
        System.out.println(twoOccurrences(stringA, stringB));
        System.out.println(lastPart(stringA, stringB));

    }

    public String lastPart(String stringA, String stringB) {
        int index = stringB.indexOf(stringA);
        if (index == -1) {
            return stringB;
        } else {
            return stringB.substring(index + stringA.length());
        }
    }

    public static void main(String[] args) {
        Part3 part = new Part3();
        part.testing();
    }

}
