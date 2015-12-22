package th.in.whs.thaisplit.engine;

import th.in.whs.thaisplit.model.Letter;
import th.in.whs.thaisplit.model.TextStream;
import th.in.whs.thaisplit.rules.stage1.*;
import th.in.whs.thaisplit.wordbreak.DictionaryWordBreaker;

public class WordExplainator {
    public RuleEvaluator<Letter, Letter> evaluator = new RuleEvaluator<>();

    public WordExplainator(){
        evaluator.addRule(new PrefixVowel());
        evaluator.addRule(new FloatingVowel());
        evaluator.addRule(new Syllable());
        evaluator.addRule(new FinalConsonant());
        evaluator.addRule(new SuffixLetterFromVowel());
        evaluator.addRule(new FinalVowel());
        evaluator.addRule(new Vowel());
        evaluator.addRule(new ClusterConsonant());
        evaluator.addRule(new PrefixConsonant());
    }

    public TextStream explain(String input){
        TextStream stream = new TextStream(input);
        for(Letter letter : stream){
            evaluator.evaluate(letter);
        }
        for(Letter letter : stream){
            evaluator.evaluate(letter);
        }
        for(Letter letter : stream){
            evaluator.evaluate(letter);
        }
        return stream;
    }

    public static void main(String[] args){
        WordExplainator explainer = new WordExplainator();
        DictionaryWordBreaker breaker = new DictionaryWordBreaker();
        String input = "LoremIpsumคือเนื้อหาจำลองแบบเรียบๆที่ใช้กันในธุรกิจงานพิมพ์หรืองานเรียงพิมพ์มันได้กลายมาเป็นเนื้อหาจำลองมาตรฐานของธุรกิจดังกล่าวมาตั้งแต่ศตวรรษที่16เมื่อเครื่องพิมพ์โนเนมเครื่องหนึ่งนำรางตัวพิมพ์มาสลับสับตำแหน่งตัวอักษรเพื่อทำหนังสือตัวอย่างLoremIpsumอยู่ยงคงกระพันมาไม่ใช่แค่เพียงห้าศตวรรษแต่อยู่มาจนถึงยุคที่พลิกโฉมเข้าสู่งานเรียงพิมพ์ด้วยวิธีทางอิเล็กทรอนิกส์และยังคงสภาพเดิมไว้อย่างไม่มีการเปลี่ยนแปลงมันได้รับความนิยมมากขึ้นในยุคค.ศ.1960เมื่อแผ่นLetrasetวางจำหน่ายโดยมีข้อความบนนั้นเป็นLoremIpsumและล่าสุดกว่านั้นคือเมื่อซอฟท์แวร์การทำสื่อสิ่งพิมพ์(DesktopPublishing)อย่างAldusPageMakerได้รวมเอาLoremIpsumเวอร์ชั่นต่างๆเข้าไว้ในซอฟท์แวร์ด้วย";

        breaker.setInput(input);
        for(String word : breaker){
            TextStream explaination = explainer.explain(word);
            for(Letter ch : explaination){
                System.out.println(ch);
            }
            System.out.println("========================");
        }
    }
}
