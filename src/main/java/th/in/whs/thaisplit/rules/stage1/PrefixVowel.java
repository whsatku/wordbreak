package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

public class PrefixVowel implements Rule<Letter, Letter> {

    private String valid = "เแโใไ";

    @Override
    public boolean match(Letter input) {
        return valid.contains(input);
    }

    @Override
    public Letter activate(Letter input) {
        input.setType(Letter.TYPE.PREFIX_VOWEL);
        if(input.getNext().getType() != Letter.TYPE.PREFIX_CONSONANT){
            input.getNext().setType(Letter.TYPE.CONSONANT);
        }

        return input;
    }
}
