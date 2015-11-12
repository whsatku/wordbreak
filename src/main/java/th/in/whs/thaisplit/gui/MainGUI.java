package th.in.whs.thaisplit.gui;

import th.in.whs.thaisplit.wordbreak.DictionaryWordBreaker;
import th.in.whs.thaisplit.wordbreak.WordBreaker;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class MainGUI extends JFrame {
    private JPanel panel1;
    private JButton executeButton;
    private JTextArea input;
    private JTextArea output;

    public MainGUI(){
        executeButton.addActionListener(e -> compute());
    }

    private void compute() {
        String text = input.getText();
        WordBreaker breaker = new DictionaryWordBreaker();
        List<String> output = breaker.run(text);

        this.output.setText(output.stream().collect(Collectors.joining(" / ")));
    }

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        MainGUI gui = new MainGUI();
        gui.setTitle("Thai word breaker");
        gui.add(gui.panel1);
        gui.setSize(400, 300);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
