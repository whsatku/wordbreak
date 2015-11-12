package th.in.whs.thaisplit.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class DictionaryWordBreaker extends TokenizeWordBreaker {

    private File dictionary = new File("dict.txt");

    @Override
    public Iterator<String> iterator() {
        return new DictionaryWordIterator();
    }

    protected class DictionaryWordIterator implements Iterator<String>{

        private String input = getInput();

        private Scanner getScanner(){
            try {
                return new Scanner(dictionary);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Dictionary not found");
            }
        }

        @Override
        public boolean hasNext() {
            return !input.isEmpty();
        }

        @Override
        public String next() {
            String currentWord = null;
            int currentMax = 0;

            while(currentWord == null) {
                Scanner scan = getScanner();
                while (scan.hasNextLine()) {
                    String word = scan.nextLine();
                    if (word.length() > currentMax && input.startsWith(word)) {
                        currentWord = word;
                        currentMax = word.length();
                    }
                }
                if(currentWord != null){
                    break;
                }
                if(input.length() > 1) {
                    input = input.substring(1);
                }else{
                    input = "";
                    return input;
                }
            }

            input = input.substring(currentMax);

            return currentWord;
        }
    }
}
