package ru.levelp.java.calculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI {

    protected ArrayList<JButton> buttons = new ArrayList<JButton>();
    protected GridLayout gridLayoutNumbers = new GridLayout(4, 3);
    protected GridLayout gridLayoutOperators = new GridLayout(2, 2);
    protected JPanel panel = new JPanel();
    protected JPanel panelOperators = new JPanel();
    protected JPanel panelEquals = new JPanel();
    protected JPanel panelOperatorsMain = new JPanel();
    protected JPanel panelC = new JPanel();
    protected JPanel panelBackspace = new JPanel();
    protected JPanel panelBackspaceC_Main = new JPanel();
    protected JPanel panelBackspaceC_Operators_Main = new JPanel();
    protected JPanel panelLabel = new JPanel();
    protected JPanel panelRight = new JPanel();
    protected JTextField textField = new JTextField();

    public void build(){
        JFrame frame = new JFrame("Calculator");
        //frameOperators.add(panelOperators);
        //frameOperators.add(panelEquals);
        frame.setBounds(100, 100, 350, 147);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        panel.setLayout(gridLayoutNumbers);
        panelOperators.setLayout(gridLayoutOperators);
        panelEquals.setLayout(new BorderLayout());
        panelOperatorsMain.setLayout(new BorderLayout());
        panelC.setLayout(new BorderLayout());
        panelBackspace.setLayout(new BorderLayout());
        panelBackspaceC_Main.setLayout(new BorderLayout());
        panelBackspaceC_Operators_Main.setLayout(new BorderLayout());
        panelLabel.setLayout(new BorderLayout());
        panelRight.setLayout(new BorderLayout());
        Font font = textField.getFont();
        textField.setFont(new Font(font.getFontName(), font.getStyle(), font.getSize()+10));

        int j=0;

        for (int i = 7; i < 10; i++) {
            buttons.add(new JButton((""+i)));
            panel.add(buttons.get(j));
            j++;
        }

        for (int i = 4; i < 7; i++) {
            buttons.add(new JButton((""+i)));
            panel.add(buttons.get(j));
            j++;
        }

        for (int i = 1; i < 4; i++) {
            buttons.add(new JButton((""+i)));
            panel.add(buttons.get(j));
            j++;
        }

        buttons.add(new JButton(("0")));
        panel.add(buttons.get(9));

        buttons.add(new JButton(("+/-")));
        panel.add(buttons.get(10));

        buttons.add(new JButton((",")));
        panel.add(buttons.get(11));

        buttons.add(new JButton(("+")));
        panelOperators.add(buttons.get(12));

        buttons.add(new JButton(("*")));
        panelOperators.add(buttons.get(13));

        buttons.add(new JButton(("-")));
        panelOperators.add(buttons.get(14));

        buttons.add(new JButton(("/")));
        panelOperators.add(buttons.get(15));

        buttons.add(new JButton(("=")));
        panelEquals.add(buttons.get(16));

        buttons.add(new JButton(("C")));
        panelC.add(buttons.get(17));

        buttons.add(new JButton(("Backspace")));
        panelBackspace.add(buttons.get(18));

        panelLabel.add(textField);



        frame.add(BorderLayout.CENTER, panel);

        panelOperatorsMain.add(BorderLayout.CENTER, panelOperators);
        panelOperatorsMain.add(BorderLayout.EAST, panelEquals);

        panelBackspaceC_Main.add(BorderLayout.WEST, panelC);
        panelBackspaceC_Main.add(BorderLayout.CENTER, panelBackspace);

        panelBackspaceC_Operators_Main.add(BorderLayout.NORTH, panelBackspaceC_Main);
        panelBackspaceC_Operators_Main.add(BorderLayout.CENTER, panelOperatorsMain);

        panelRight.add(BorderLayout.NORTH, panelLabel);
        panelRight.add(BorderLayout.CENTER, panelBackspaceC_Operators_Main);

        frame.add(BorderLayout.EAST, panelRight);
       // frame.add(BorderLayout.EAST, panelRight);


        frame.setResizable(false);
        frame.setVisible(true);

    }

}
