package coursera.xujinqi.cousera2.week6;

import java.util.ArrayList;

import edu.duke.FileResource;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreq;

    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreq = new ArrayList<>();
    }

    public void findUnique() {
        myWords.clear();
        myFreq.clear();
        FileResource fr = new FileResource();

        for (String s : fr.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreq.add(1);
            } else {
                myFreq.set(index, myFreq.get(index) + 1);
            }
        }
    }

    public void printWords() {
        System.out.println(myWords.size());

        for (int k = 0; k < myWords.size(); k++) {
            System.out.println(myFreq.get(k) + "\t" + myWords.get(k));
        }
    }

    public int findIndexOfMax() {
        int max = 0;
        int maxInd = 0;
        for (int i = 0; i < myFreq.size(); i++) {
            if (myFreq.get(i) > max) {
                max = myFreq.get(i);
                maxInd = i;
            }
        }
        System.out.println(myWords.get(maxInd));
        System.out.println(myFreq.get(maxInd));
        return maxInd;
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.findUnique();
        System.out.println(wf.findIndexOfMax());

        // wf.printWords();
    }
}
