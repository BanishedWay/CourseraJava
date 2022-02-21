package coursera.xujinqi.cousera2.week5;

public class Test1 {
    public static void main(String[] args) {
        // System.out.println(reverse("Computer"));
        // int x = 'a';
        // System.out.println(x);
        // System.out.println((char) (x + 5));
        System.out.println(Character.toLowerCase('A'));
    }

    public static String reverse(String s) {
        String ret = "";
        for (int k = 0; k < s.length(); k += 2) {
            ret = s.charAt(k) + ret;
        }
        return ret;
    }
}
