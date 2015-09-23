package th.in.whs.thaisplit.model;

import java.lang.reflect.Array;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TextStream implements CharSequence, Iterable<Letter> {
    private List<Letter> tokens;
    private int created = 0;

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
        if(index == tokens.size()){
            return null;
        }
        return tokens.get(index + 1);
    }

    @Override
    public String toString() {
        return tokens.stream().collect(Collectors.joining(""));
    }

    @Override
    public int length() {
        return tokens.stream().mapToInt(x -> x.length()).sum();
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
