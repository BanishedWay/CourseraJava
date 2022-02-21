package coursera.xujinqi.cousera2.week5;

public class Assignment1 {

    /**
     * 
     * @param ch
     * @return
     */
    public boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' || ch == 'i' || ch == 'I' || ch == 'o' || ch == 'O'
                || ch == 'u' || ch == 'U') {
            return true;
        }
        return false;
    }

    public void testVowel() {
        System.out.println(isVowel('a'));
        System.out.println(isVowel('F'));
    }

    public String replaceVowels(String message, char ch) {
        StringBuilder sb = new StringBuilder(message);
        for (int i = 0; i < sb.length(); i++) {
            if (isVowel(sb.charAt(i))) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }

    public void testReplaceVowel() {
        System.out.println(replaceVowels("Hello world", 's'));
        System.out.println(replaceVowelsSecond("Mary Bella Abracadabra", 'a'));

    }

    public String replaceVowelsSecond(String message, char ch) {
        StringBuilder sb = new StringBuilder(message);
        for (int i = 0; i < sb.length(); i++) {
            if (isVowel(sb.charAt(i))) {
                if (i % 2 == 0) {
                    ch = '*';
                } else {
                    ch = '+';
                }
                sb.setCharAt(i, ch);
            }

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new Assignment1().testReplaceVowel();
    }
}
