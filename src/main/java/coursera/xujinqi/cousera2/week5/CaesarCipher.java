package coursera.xujinqi.cousera2.week5;

public class CaesarCipher {

    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String encrypt(String input, int key) {

        StringBuilder sb = new StringBuilder(input);

        for (int i = 0; i < sb.length(); i++) {
            char encrypted = encrypt(sb.charAt(i), key);
            sb.setCharAt(i, encrypted);

        }
        return sb.toString();
    }

    public char encrypt(char ch, int key) {
        char chUC = Character.toUpperCase(ch);
        int cInd = ALPHABET.indexOf(chUC);

        if (cInd == -1) {
            return ch;
        }

        int endInd = (cInd + key) % 26;
        char enCh = ALPHABET.charAt(endInd);

        if (Character.isUpperCase(ch)) {
            return enCh;
        } else {
            return Character.toLowerCase(enCh);
        }
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);

        int key;
        for (int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                key = key1;
            } else {
                key = key2;
            }
            char ch = encrypted.charAt(i);
            encrypted.setCharAt(i, encrypt(ch, key));
        }
        return encrypted.toString();
    }

    public void testCaesar() {
        // int key = 17;
        // System.out.println(
        // encrypt("Can you imagine life WITHOUT the internet AND computers in your
        // pocket?", 15));
        // System.out.println(encrypt("First Legion", 17));
        // System.out.println(encrypt("Cfopq Ibdflk", 3));

        // System.out.println(encryptTwoKeys(
        // "Can you imagine life WITHOUT the internet AND computers in your pocket?", 8,
        // 21));
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        // String encrypted = encrypt(message, key);
        // System.out.println("key is " + key + "\n" + encrypted);
        // String decrypted = encrypt(encrypted, 26 - key);
        // System.out.println(decrypted);
        System.out.println(
                encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8));
    }

    public static void main(String[] args) {
        new CaesarCipher().testCaesar();
    }
}
