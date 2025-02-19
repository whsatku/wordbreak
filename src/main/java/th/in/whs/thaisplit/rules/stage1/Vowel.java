package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

/**
 * Created by Nut on 12/22/2015 AD.
 */
public class Vowel implements Rule<Letter, Letter> {

    private String valid = "ระำๅเแโใิีึืุูไะา";

    @Override
    public boolean match(Letter input) {
        if(input.getLetter().equals("ร")){
            if(input.getNext() != null) {
                return input.getNext().getLetter().equals("ร");
            }
        }
        return valid.contains(input);
    }

    @Override
    public Letter activate(Letter input) {
        input.setType(Letter.TYPE.VOWEL);
        Letter next = input.getNext();
        if(input.getLetter().equals("ร") && next.getLetter().equals("ร")){
            input.getNext().setType(Letter.TYPE.VOWEL);
            input.getPrevious().setType(Letter.TYPE.CONSONANT);
        }
        return input;
    }

}
