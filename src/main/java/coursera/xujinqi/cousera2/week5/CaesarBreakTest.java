package coursera.xujinqi.cousera2.week5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CaesarBreakTest {
    CaesarCipher cc = new CaesarCipher();
    CaesarBreak cb = new CaesarBreak();

    @Test
    public void testDecrypt() {
        int key = 10;
        String msg = "This is a text to be tested.";
        String encrypted = cc.encrypt(msg, key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + cb.decrypt(encrypted));

        assertEquals(msg, cb.decrypt(encrypted, key));
    }

    @Test
    public void testDecryptOneKey() {
        String org = "This is message to be decrypted with my methods!@@@ eee!!";
        String enc = cc.encrypt(org, 20);
        String dec = cb.decrypt(enc);
        assertEquals(org, dec);
    }

    @Test
    public void testCounters() {
        String txt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        txt += txt.toLowerCase();
        int[] counts = cb.countLetters(txt);
        for (int i = 0; i < counts.length; i++) {
            System.out.printf("%d %d\n", i, counts[i]);
        }
    }

    private int mi(String txt) {
        return cb.maxIndex(cb.countLetters(txt));
    }

    @Test
    public void testMaxIndex() {
        String txt = "aaaabbc";
        assertEquals(0, mi(txt));

        txt = "aaAbbBBcC";
        assertEquals(1, mi(txt));

        txt = "aaAbbBBcCcccccc";
        assertEquals(2, mi(txt));
    }

    @Test
    public void testHalfOfString() {
        String txt = "Qbkm Zgis";

        assertEquals("Qk gs", cb.halfOfString(txt, 0));
        assertEquals("bmZi", cb.halfOfString(txt, 1));
    }

    @Test
    public void testDecryptTwoKeys() {

        String msg = "aaaXXXEEEeeeXXXYYYeeeeeeeeeeeeeeeeeEEEEEEE";
        String encoded = cc.encryptTwoKeys(msg, 11, 22);
        String decoded = cb.decryptTwoKeys(encoded);
        System.out.println(msg);
        System.out.println(encoded);
        System.out.println(decoded);
        assertEquals(msg, decoded);

    }

}
