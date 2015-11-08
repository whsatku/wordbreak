package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

public class FinalConsonant implements Rule<Letter, Letter> {

    private String valid = "งมยวนญณรลฬกขคฆดจชซฎฏฐฑฒตถทธศษสบปภพฟ";

    @Override
    public boolean match(Letter input) {
        return input.getType() == null && valid.contains(input);
    }

    @Override
    public Letter activate(Letter input) {
        input.setType(Letter.TYPE.FINAL_CONSONANT);

        Letter previous = input.getPrevious();
        while(previous.getType() == Letter.TYPE.SYLLABLE || previous.getType() == Letter.TYPE.FLOATING_VOWEL){
            previous = previous.getPrevious();
        }

        if(!previous.isFlippedPreviousToConsonant() && previous.getType() == Letter.TYPE.FINAL_CONSONANT){
            previous.setType(Letter.TYPE.CONSONANT);
            input.setFlippedPreviousToConsonant(true);
        }

        return input;
    }
}
