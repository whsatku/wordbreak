package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

public class FinalVowel implements Rule<String, Letter> {

    private String valid = "ะำๅ์";

    @Override
    public boolean match(String input) {
        return valid.contains(input);
    }

    @Override
    public Letter activate(String input) {
        return new Letter(input, Letter.TYPE.FINAL_VOWEL);
    }
}
