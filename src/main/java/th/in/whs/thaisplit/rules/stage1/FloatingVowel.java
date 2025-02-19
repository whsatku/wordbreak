package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

public class FloatingVowel implements Rule<Letter, Letter> {

    private String valid = "ิีึืุู";

    @Override
    public boolean match(Letter input) {
        if(input.getLetter().equals("ั")){
            if(input.getNext() != null){
                return true;
            }
        }
        if(input.getLetter().equals("็")){
            if(input.getNext() != null){
                return true;
            }
        }
        return valid.contains(input);
    }

    @Override
    public Letter activate(Letter input) {
        input.setType(Letter.TYPE.FLOATING_VOWEL);
        if(input.getPrevious().getType() == null){
            input.getPrevious().setType(Letter.TYPE.CONSONANT);
        }
        if(input.getLetter().equals("ั") || input.getLetter().equals("็")){
            input.getNext().setType(Letter.TYPE.FINAL_CONSONANT);
        }

        return input;
    }
}

