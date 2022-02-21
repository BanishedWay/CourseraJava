package coursera.xujinqi.cousera2.week6;

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
    private ArrayList<String> characterName;
    private ArrayList<Integer> myFreq;

    public CharactersInPlay() {
        characterName = new ArrayList<>();
        myFreq = new ArrayList<>();
    }

    public void update(String person) {
        if (!characterName.contains(person)) {
            characterName.add(person);
            myFreq.add(1);
        } else {
            int index = characterName.indexOf(person);
            myFreq.set(index, myFreq.get(index) + 1);
        }
    }

    public void findAllCharacters() {
        characterName.clear();
        myFreq.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            if (line.contains(".")) {
                line = line.trim();
                int index = line.indexOf(".");
                if (index != -1) {
                    String person = line.substring(0, index);
                    update(person);
                }
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < myFreq.size(); i++) {
            int number = myFreq.get(i);
            if (number <= num2 && number >= num1) {
                System.out.println(characterName.get(i) + " " + number);
            }
        }
    }

    public void tester() {
        findAllCharacters();
        // for (int i = 0; i < characterName.size(); i++) {
        // if (myFreq.get(i) > 100) {
        // System.out.println(characterName.get(i) + "'s number of speaking parts: " +
        // myFreq.get(i));
        // }
        // }
        charactersWithNumParts(10, 15);

        // characterName.remove(maxCharacters());
        // characterName.remove(maxCharacters());
        // int index = maxCharacters();
        // System.out.println(characterName.get(index));
        // System.out.println(myFreq.get(index));

    }

    public int maxCharacters() {
        int maxId = -1;
        int maxCount = 0;
        for (int i = 0; i < myFreq.size(); i++) {
            if (maxCount < myFreq.get(i)) {
                maxCount = myFreq.get(i);
                maxId = i;
            }
        }
        return maxId;
    }

    public static void main(String[] args) {
        new CharactersInPlay().tester();
    }
}
