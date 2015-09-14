package th.in.whs.thaisplit.engine;

import java.text.BreakIterator;
import java.util.Iterator;
import java.util.Locale;

public class JavaWordBreaker extends TokenizeWordBreaker {

    private Locale locale = new Locale("th");

    protected BreakIterator getWordBreaker(){
        return BreakIterator.getWordInstance(locale);
    }

    @Override
    public Iterator<String> iterator() {
        return new JavaWordIterator();
    }

    private class JavaWordIterator implements  Iterator<String>{

        private int current, next;
        private boolean hasNext = true;
        private BreakIterator iterator;

        public JavaWordIterator(){
            iterator = getWordBreaker();
            iterator.setText(getInput());

            current = iterator.first();
            next = iterator.next();
            checkHasNext();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public String next() {
            String output = getInput().substring(current, next);
            current = next;
            next = iterator.next();
            checkHasNext();
            return output;
        }

        private void checkHasNext(){
            if(next ==  BreakIterator.DONE){
                hasNext = false;
            }
        }
    }
}
