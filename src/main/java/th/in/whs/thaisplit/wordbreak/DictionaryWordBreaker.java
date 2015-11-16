package th.in.whs.thaisplit.wordbreak;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DictionaryWordBreaker extends TokenizeWordBreaker {

    private static List<String> words;

    public DictionaryWordBreaker(){
    }

    protected InputStream getDictionary(){
        return getClass().getResourceAsStream("dict.txt");
    }

    private Scanner getScanner(){
        return new Scanner(getDictionary());
    }

    protected void loadWordToDictionary(){
        words = new LinkedList<>();
        Scanner scan = getScanner();
        while (scan.hasNextLine()) {
            String word = scan.nextLine();
            words.add(word);
        }
    }

    @Override
    public Iterator<String> iterator() {
        if(words == null) {
            loadWordToDictionary();
        }
        return new DictionaryWordIterator();
    }

    protected class DictionaryWordIterator implements Iterator<String>{

        private String input = getInput();

        @Override
        public boolean hasNext() {
            return !input.isEmpty();
        }

        @Override
        public String next() {
            String currentWord = null;
            int currentMax = 0;

            while(currentWord == null) {
                for(String word : words){
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

        @Override
        public void remove() {
        }
    }
}
