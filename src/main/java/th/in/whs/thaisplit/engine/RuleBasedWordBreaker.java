package th.in.whs.thaisplit.engine;

import th.in.whs.thaisplit.model.Letter;
import th.in.whs.thaisplit.rules.stage1.FinalVowel;
import th.in.whs.thaisplit.rules.stage1.FloatingVowel;
import th.in.whs.thaisplit.rules.stage1.PrefixVowel;
import th.in.whs.thaisplit.rules.stage1.Syllable;

import java.util.List;

public class RuleBasedWordBreaker implements WordBreaker {

    protected void stage1(String input){
        RuleEvaluator<String, Letter> evaluator = new RuleEvaluator<>();
        evaluator.addRule(new PrefixVowel());
        evaluator.addRule(new FloatingVowel());
        evaluator.addRule(new Syllable());
        evaluator.addRule(new FinalVowel());
        for(int i = 0, j = input.length(); i < j; i++){
            String letter = input.substring(i, i+1);
            Letter result = evaluator.evaluate(letter);
//            System.out.print(letter);
            if(result != null) System.out.println(result);
            if(result != null &&
                    (result.getType() == Letter.TYPE.FINAL_CONSONANT || result.getType() == Letter.TYPE.FINAL_VOWEL)
            ){
                System.out.println();
            }
            if(letter.equals(" ")){
                System.out.println();
            }
        }
    }

    @Override
    public List<String> run(String input) {
        stage1(input);
        return null;
    }

    public static void main(String[] args){
        RuleBasedWordBreaker breaker = new RuleBasedWordBreaker();
        breaker.run("LoremIpsumคือเนื้อหาจำลองแบบเรียบๆที่ใช้กันในธุรกิจงานพิมพ์หรืองานเรียงพิมพ์มันได้กลายมาเป็นเนื้อหาจำลองมาตรฐานของธุรกิจดังกล่าวมาตั้งแต่ศตวรรษที่16เมื่อเครื่องพิมพ์โนเนมเครื่องหนึ่งนำรางตัวพิมพ์มาสลับสับตำแหน่งตัวอักษรเพื่อทำหนังสือตัวอย่างLoremIpsumอยู่ยงคงกระพันมาไม่ใช่แค่เพียงห้าศตวรรษแต่อยู่มาจนถึงยุคที่พลิกโฉมเข้าสู่งานเรียงพิมพ์ด้วยวิธีทางอิเล็กทรอนิกส์และยังคงสภาพเดิมไว้อย่างไม่มีการเปลี่ยนแปลงมันได้รับความนิยมมากขึ้นในยุคค.ศ.1960เมื่อแผ่นLetrasetวางจำหน่ายโดยมีข้อความบนนั้นเป็นLoremIpsumและล่าสุดกว่านั้นคือเมื่อซอฟท์แวร์การทำสื่อสิ่งพิมพ์(DesktopPublishing)อย่างAldusPageMakerได้รวมเอาLoremIpsumเวอร์ชั่นต่างๆเข้าไว้ในซอฟท์แวร์ด้วย");
    }
}
