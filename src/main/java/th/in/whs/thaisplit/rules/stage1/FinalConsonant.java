package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

public class FinalConsonant implements Rule<Letter, Letter> {

    private String valid = "งมยวนญณรลฬกขคฆดจชซฎฏฐฑฒตถทธศษสบปภพฟ";

    @Override
    public boolean match(Letter input) {
        boolean allowedNext = false;
        Letter next = input.getNext();
        if (next == null) {
            allowedNext = true;
        }else if (next.getType() == null){
            allowedNext = false;
        }else{
            switch(next.getType()){
                case FINAL_VOWEL:
                    allowedNext = true;
                    break;
                default:
            }
        }
        return input.getType() == null && valid.contains(input) && allowedNext;
    }

    @Override
    public Letter activate(Letter input) {
        input.setType(Letter.TYPE.FINAL_CONSONANT);

        return input;
    }
}
