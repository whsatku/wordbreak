package th.in.whs.thaisplit.rules.stage1;

import th.in.whs.thaisplit.engine.Rule;
import th.in.whs.thaisplit.model.Letter;

public class PrefixConsonant implements Rule<Letter, Letter> {

    @Override
    public boolean match(Letter input) {
        Letter next = input.getNext();
        if(input.getLetter().equals("อ")){
            if(input.getPrevious() == null && next.equals("ย")){
                return true;
            }
        }
        if(input.getLetter().equals("ห")){
            if(next.getType() == Letter.TYPE.CONSONANT){
                return true;
            }
        }
        return false;
    }

    @Override
    public Letter activate(Letter input) {
        input.setType(Letter.TYPE.PREFIX_CONSONANT);

        return input;
    }
}

