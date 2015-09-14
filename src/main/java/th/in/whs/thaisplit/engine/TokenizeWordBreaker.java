package th.in.whs.thaisplit.engine;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class TokenizeWordBreaker implements WordBreaker, Iterable<String> {

    private String input;

    @Override
    public List<String> run(String input) {
        setInput(input);

        List<String> output = new LinkedList<>();
        Iterator<String> iterator = iterator();

        while(iterator.hasNext()){
            output.add(iterator.next());
        }

        return output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public abstract Iterator<String> iterator();
}
