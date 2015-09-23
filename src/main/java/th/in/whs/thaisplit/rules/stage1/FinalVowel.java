package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

public class FinalVowel implements Rule<Letter, Letter> {

    private String valid = "ะำๅ์";

    @Override
    public boolean match(Letter input) {
        return valid.contains(input);
    }

    @Override
    public Letter activate(Letter input) {
        input.setType(Letter.TYPE.FINAL_VOWEL);

        return input;
    }
}
