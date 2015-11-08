package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

/**
 * Deals with the suffix letter in เอีย อัว เอือ
 * Depends on Syllable detection
 */
public class SuffixLetterFromVowel implements Rule<Letter, Letter> {

    @Override
    public boolean match(Letter input) {
        // it must be a letter
        String letter = input.getLetter();
        if(!("ยวอ".contains(letter))){
            return false;
        }
        Letter previous = input.getPrevious();
        if(previous.getType() == Letter.TYPE.SYLLABLE){
            previous = previous.getPrevious();
        }

        // เอีย
        if(letter.equals("ย")){
            return previous.getLetter().equals("ี");
        }
        // อัว
        if(letter.equals("ว")){
            return previous.getLetter().equals("ั");
        }
        // เอือ
        if(letter.equals("อ")){
            return previous.getLetter().equals("ื");
        }

        // should be unreachable
        return false;
    }

    @Override
    public Letter activate(Letter input) {
        input.setType(Letter.TYPE.FINAL_CONSONANT);

        return input;
    }
}
