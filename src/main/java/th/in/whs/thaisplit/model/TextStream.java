package th.in.whs.thaisplit.model;

import th.in.whs.thaisplit.wordbreak.DictionaryTranslator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextStream implements CharSequence, Iterable<Letter> {
    private List<Letter> tokens;

    public TextStream(String input){
        tokens = new ArrayList<>(input.length());
        for(int i = 0, j = input.length(); i < j; i++){
            tokens.add(new Letter(this, String.valueOf(input.charAt(i))));
        }
    }

    public Letter getPrevious(Letter letter){
        int index = tokens.indexOf(letter);
        if(index == -1){
            throw new ArrayIndexOutOfBoundsException("token is not in list");
        }
        if(index == 0){
            return null;
        }
        return tokens.get(index - 1);
    }

    public Letter getNext(Letter letter){
        int index = tokens.indexOf(letter);
        if(index == -1){
            throw new ArrayIndexOutOfBoundsException("token is not in list");
        }
        if(index == tokens.size() - 1){
            return null;
        }
        return tokens.get(index + 1);
    }

    public String word_meaning(){
        DictionaryTranslator dictionaryTranslator = DictionaryTranslator.getInstance();
        return dictionaryTranslator.search_meaning(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Letter token : tokens){
            sb.append(token.toString());
        }

        return sb.toString();
    }

    @Override
    public int length() {
        int sum = 0;

        for(Letter token : tokens){
            sum += token.length();
        }

        return sum;
    }

    @Override
    public char charAt(int i) {
        for(int x = 0, size = tokens.size(); x < size; x++){
            Letter token = tokens.get(x);
            if(token.length() > i){
                return token.charAt(i);
            }
            i -= token.length();
        }
        return 0;
    }

    @Override
    public CharSequence subSequence(int i, int j) {
        return toString().subSequence(i, j);
    }

    @Override
    public Iterator<Letter> iterator() {
        return tokens.iterator();
    }
}
