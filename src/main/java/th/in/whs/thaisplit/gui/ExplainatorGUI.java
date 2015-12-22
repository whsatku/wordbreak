package th.in.whs.thaisplit.gui;

import th.in.whs.thaisplit.engine.WordExplainator;
import th.in.whs.thaisplit.model.Letter;
import th.in.whs.thaisplit.model.TextStream;
import th.in.whs.thaisplit.wordbreak.DictionaryWordBreaker;
import th.in.whs.thaisplit.wordbreak.TokenizeWordBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExplainatorGUI extends JFrame {
    private JPanel panel1;
    private JTextField input;
    private JLabel explaination;
    private JPanel result;
    private JButton next;

    private WordExplainator explainator = new WordExplainator();
    private TokenizeWordBreaker wordBreaker = new DictionaryWordBreaker();
    private Iterator<String> wordIterator;
    private Map<Letter.TYPE, Color> colorMap;
    private Font wordFont = new Font("Dialog", Font.PLAIN, 26);

    public ExplainatorGUI(){
        result.setVisible(false);
        input.addActionListener(x -> onInputAction());
        next.addActionListener(x -> nextWord());

        colorMap = new HashMap<>(Letter.TYPE.values().length);
        colorMap.put(Letter.TYPE.PREFIX_VOWEL, Color.YELLOW);
        colorMap.put(Letter.TYPE.PREFIX_CONSONANT, Color.GREEN);
        colorMap.put(Letter.TYPE.CONSONANT, Color.BLACK);
        colorMap.put(Letter.TYPE.FLOATING_VOWEL, Color.RED);
        colorMap.put(Letter.TYPE.SYLLABLE, Color.BLUE);
        colorMap.put(Letter.TYPE.FINAL_CONSONANT, Color.MAGENTA);
        colorMap.put(Letter.TYPE.FINAL_VOWEL, Color.ORANGE);
        colorMap.put(Letter.TYPE.VOWEL, Color.GRAY);
    }

    private void onInputAction() {
        wordBreaker.setInput(input.getText());
        wordIterator = wordBreaker.iterator();
        nextWord();
    }

    private void nextWord(){
        if(!wordIterator.hasNext()){
            next.setEnabled(false);
            return;
        }
        TextStream word = explainator.explain(wordIterator.next());
        result.setVisible(false);

        result.removeAll();

        for(Letter letter : word){
            final JLabel label = new JLabel(letter.getLetter());
            label.setFont(wordFont);
            if(letter.getType() != null) {
                label.setForeground(colorMap.get(letter.getType()));
                label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        explaination.setText(letter.getType().toString());
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        explaination.setText(" ");
                    }
                });
            }
            result.add(label);
        }

        next.setEnabled(wordIterator.hasNext());

        result.add(next);
        result.setVisible(true);
    }

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        ExplainatorGUI gui = new ExplainatorGUI();
        gui.setTitle("Thai word breaker");
        gui.add(gui.panel1);
        gui.setSize(400, 300);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
