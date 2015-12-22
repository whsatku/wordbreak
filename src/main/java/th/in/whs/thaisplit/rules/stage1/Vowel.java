package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

/**
 * Created by Nut on 12/22/2015 AD.
 */
public class Vowel implements Rule<Letter, Letter> {

    private String valid = "ะำๅเแโใิีึืุูไะา";

    @Override
    public boolean match(Letter input) {
        return valid.contains(input);
    }

    @Override
    public Letter activate(Letter input) {
        input.setType(Letter.TYPE.VOWEL);

        return input;
    }

}
