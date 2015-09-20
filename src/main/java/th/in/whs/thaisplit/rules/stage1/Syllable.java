package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

public class Syllable implements Rule<String, Letter> {

    private String valid = "่้๊๋";

    @Override
    public boolean match(String input) {
        return valid.contains(input);
    }

    @Override
    public Letter activate(String input) {
        return new Letter(input, Letter.TYPE.SYLLABLE);
    }
}


