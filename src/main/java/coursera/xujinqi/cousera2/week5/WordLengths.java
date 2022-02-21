package coursera.xujinqi.cousera2.week5;

import edu.duke.FileResource;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {

        for (String word : resource.words()) {
            int wordLen = Math.min(wordLength(word), counts.length);
            counts[wordLen]++;
        }
    }

    private int wordLength(String word) {
        int len = 0;
        for (char ch : word.toCharArray()) {
            if (Character.isLetter(ch)) {
                len++;
            }
        }
        return len;
    }

    public int indexOfMax(int[] values) {
        int inOfMax = 0;
        int maxSoFar = values[0];

        for (int i = 1; i < values.length; i++) {
            if (values[i] > maxSoFar) {
                maxSoFar = values[i];
                inOfMax = i;
            }
        }
        return inOfMax;
    }

    public void testCountWordLengths() {
        int[] counts = new int[31];
        FileResource fr = new FileResource();

        countWordLengths(fr, counts);

        for (int i = 0; i < counts.length; i++) {
            System.out.printf("%d: %d\n", i, counts[i]);
        }

        System.out.println("Largest: " + indexOfMax(counts));
    }

    public static void main(String[] args) {
        new WordLengths().testCountWordLengths();
    }
}
