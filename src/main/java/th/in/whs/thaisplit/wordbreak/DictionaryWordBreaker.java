package th.in.whs.thaisplit.wordbreak;

import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class DictionaryWordBreaker extends TokenizeWordBreaker {

    @Override
    public Iterator<String> iterator() {
        return new DictionaryWordIterator();
    }

    protected class DictionaryWordIterator implements Iterator<String>{

        private String input = getInput();

        private InputStream getDictionary(){
            return getClass().getResourceAsStream("dict.txt");
        }

        private Scanner getScanner(){
            return new Scanner(getDictionary());
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
