package th.in.whs.thaisplit.model;

public class Letter implements CharSequence {

    public enum TYPE {
        PREFIX_VOWEL, // สระต้น
        PREFIX_CONSONANT, // อักษรนำ
        CLUSTER_CONSONANT, //อักษรควบ
        CONSONANT, // พยัญชนะต้น
        FLOATING_VOWEL,
        VOWEL, //สระ
        SYLLABLE, // วรรณยุกต์
        FINAL_CONSONANT, // ตัวสะกด
        FINAL_VOWEL

    }

    private String letter;
    private TYPE type = null;
    private TextStream parent = null;
    private boolean flippedPreviousToConsonant = false;

    public Letter(String letter) {
        this.letter = letter;
    }

    public Letter(String letter, TYPE type) {
        this.letter = letter;
        this.type = type;
    }

    public Letter(TextStream textStream, String letter) {
        this(letter);
        parent = textStream;
    }

    @Override
    public int length() {
        return letter.length();
    }

    @Override
    public char charAt(int i) {
        return letter.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int j) {
        return letter.subSequence(i, j);
    }

    public String toString(){
        if(type != null){
            String displayLetter = letter;

            if(type == TYPE.FLOATING_VOWEL || type == TYPE.SYLLABLE){
                displayLetter = "อ" + letter;
            }

            return String.format("%s [%s%s]", displayLetter, type.toString(), isFlippedPreviousToConsonant() ? "!" : "");
        }else{
            return letter;
        }
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Letter getNext(){
        return parent.getNext(this);
    }

    public Letter getPrevious(){
        return parent.getPrevious(this);
    }

    public boolean isFlippedPreviousToConsonant() {
        return flippedPreviousToConsonant;
    }

    public void setFlippedPreviousToConsonant(boolean flippedPreviousToConsonant) {
        this.flippedPreviousToConsonant = flippedPreviousToConsonant;
    }
}
