package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

public class FloatingVowel implements Rule<Letter, Letter> {

    private String valid = "ิีึืุู";

    @Override
    public boolean match(Letter input) {
        if (input.getLetter().equals("ั")){
            if (input.getNext() != null){
                return true;
            }
        }
        return valid.contains(input);
    }

    @Override
    public Letter activate(Letter input) {
        input.setType(Letter.TYPE.FLOATING_VOWEL);
        input.getPrevious().setType(Letter.TYPE.CONSONANT);

        return input;
    }
}

