package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

/**
 * Created by Nut on 12/22/2015 AD.
 */

public class ClusterConsonant implements Rule<Letter, Letter> {

    private String valid = "รลว";

    @Override
    public boolean match(Letter input) {
        Letter previous = input.getPrevious();
        if(previous == null ||
                previous.getType() == Letter.TYPE.VOWEL ||
                previous.getType() == Letter.TYPE.PREFIX_VOWEL){ // case รร
            return false;
        }
        Letter next = input.getNext();
        if(valid.contains(input.getLetter()) && next != null){
            if(next.getType() == Letter.TYPE.SYLLABLE || next.getType() == Letter.TYPE.FLOATING_VOWEL){
                if(next.getNext() != null) {
                    next = next.getNext();
                }
            }
            return next.getType() == Letter.TYPE.VOWEL ||
                    next.getType() == Letter.TYPE.FINAL_VOWEL ||
                    next.getType() == Letter.TYPE.FINAL_CONSONANT;
        }

        return false;
    }

    @Override
    public Letter activate(Letter input) {
        input.setType(Letter.TYPE.CLUSTER_CONSONANT);
        if(input.getPrevious().getType() == null){
            input.getPrevious().setType(Letter.TYPE.CONSONANT);
        }

        return input;
    }
}
