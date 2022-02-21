package coursera.xujinqi.cousera2.week5;

import edu.duke.FileResource;

public class CaesarBreak {
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public int[] countLetters(String message) {
        int[] counts = new int[26];
        for (char c : message.toCharArray()) {
            char cuc = Character.toUpperCase(c);
            if (Character.isAlphabetic(cuc)) {
                counts[ALPHABET.indexOf(cuc)]++;
            }
        }
        return counts;
    }

    public String decrypt(String encrypted) {
        return decrypt(encrypted, getKey(encrypted));
    }

    public String decrypt(String encrypted, int key) {
        CaesarCipher cc = new CaesarCipher();

        return cc.encrypt(encrypted, 26 - key);
    }

    public int maxIndex(int[] freqs) {
        int indOfMax = 0;
        int maxSoFar = freqs[0];

        for (int i = 1; i < freqs.length; i++) {
            if (freqs[i] > maxSoFar) {
                maxSoFar = freqs[i];
                indOfMax = i;
            }
        }
        return indOfMax;
    }

    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();

        for (int i = start; i < message.length(); i += 2) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int getKey(String s) {
        int[] counts = countLetters(s);
        int maxInd = maxIndex(counts);
        final int IND_E = 4;
        int key = maxInd - IND_E;
        if (key < 0) {
            System.out.println(key + 26);
            return key + 26;
        } else {
            System.out.println(key);
            return key;
        }
    }

    public String mix(String s0, String s1) {
        StringBuilder sb = new StringBuilder(s0 + s1);
        for (int i = 0; i < s0.length(); i++) {
            sb.setCharAt(i * 2, s0.charAt(i));
        }

        for (int i = 0; i < s1.length(); i++) {
            sb.setCharAt(i * 2 + 1, s1.charAt(i));
        }
        return sb.toString();
    }

    public String decryptTwoKeys(String encrypted) {
        String s0 = halfOfString(encrypted, 0);
        String s1 = halfOfString(encrypted, 1);
        String dec0 = decrypt(s0);
        String dec1 = decrypt(s1);

        return mix(dec0, dec1);
    }

    public String decrypt(String encrypted, int key0, int key1) {

        String s0 = halfOfString(encrypted, 0);
        String s1 = halfOfString(encrypted, 1);

        String dec0 = decrypt(s0, key0);
        String dec1 = decrypt(s1, key1);

        return mix(dec0, dec1);

    }

    public String decryptFileTwoKeys() {
        FileResource fr = new FileResource();
        return decryptTwoKeys(fr.asString());
    }

    public static void main(String[] args) {
        String s = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println(new CaesarBreak().decryptFileTwoKeys());
    }
}
