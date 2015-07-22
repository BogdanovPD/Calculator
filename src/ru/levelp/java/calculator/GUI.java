package ru.levelp.java.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {

    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private GridLayout gridLayoutNumbers = new GridLayout(4, 3);
    private GridLayout gridLayoutOperators = new GridLayout(2, 2);
    private JPanel panel = new JPanel();
    private JPanel panelOperators = new JPanel();
    private JPanel panelEquals = new JPanel();
    private JPanel panelOperatorsMain = new JPanel();
    private JPanel panelC = new JPanel();
    private JPanel panelBackspace = new JPanel();
    private JPanel panelBackspaceC_Main = new JPanel();
    private JPanel panelBackspaceC_Operators_Main = new JPanel();
    private JPanel panelLabel = new JPanel();
    private JPanel panelRight = new JPanel();
    private JLabel textField = new JLabel("0");
    private double current;
    private String operation = "";
    private  boolean clear = false;
    private NumButtonListener NBL = new NumButtonListener(this);
    private OperationListener aL = new OperationListener(this);

    public void build(){
        JFrame frame = new JFrame("Calculator");
        //frameOperators.add(panelOperators);
        //frameOperators.add(panelEquals);
        frame.setBounds(100, 100, 350, 138);
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
        Font font = getTextField().getFont();
        getTextField().setFont(new Font(font.getFontName(), font.getStyle(), font.getSize() + 10));

        int j=0;
        //789 456 123 = 012 345 678(ind)
        for (int i = 7; i < 10; i++) {
            buttons.add(new JButton((""+i)));
            buttons.get(j).addActionListener(NBL);
            panel.add(buttons.get(j));
            j++;
        }

        for (int i = 4; i < 7; i++) {
            buttons.add(new JButton((""+i)));
            buttons.get(j).addActionListener(NBL);
            panel.add(buttons.get(j));
            j++;
        }

        for (int i = 1; i < 4; i++) {
            buttons.add(new JButton((""+i)));
            buttons.get(j).addActionListener(NBL);
            panel.add(buttons.get(j));
            j++;
        }

        buttons.add(new JButton(("0")));
        buttons.get(9).addActionListener(NBL);
        panel.add(buttons.get(9));

        buttons.add(new JButton(("+/-")));
        panel.add(buttons.get(10));

        buttons.add(new JButton((",")));
        panel.add(buttons.get(11));

        buttons.add(new JButton(("+")));
        buttons.get(12).addActionListener(aL);
        panelOperators.add(buttons.get(12));

        buttons.add(new JButton(("*")));
        buttons.get(13).addActionListener(aL);
        panelOperators.add(buttons.get(13));

        buttons.add(new JButton(("-")));
        buttons.get(14).addActionListener(aL);
        panelOperators.add(buttons.get(14));

        buttons.add(new JButton(("/")));
        buttons.get(15).addActionListener(aL);
        panelOperators.add(buttons.get(15));

        buttons.add(new JButton(("=")));
        buttons.get(16).addActionListener(aL);
        panelEquals.add(buttons.get(16));

        buttons.add(new JButton(("C")));
        buttons.get(17).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTextField().setText("0");
                setCurrent(0);
                setClear(false);
                setOperation("");
            }
        });
        panelC.add(buttons.get(17));

        buttons.add(new JButton(("Backspace")));
        buttons.get(18).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getTextField().getText().length()==1){
                    getTextField().setText("0");
                }else
                getTextField().setText(getTextField().getText().substring(0, getTextField().getText().length() - 1));
            }
        });
        panelBackspace.add(buttons.get(18));

        panelLabel.add(getTextField());



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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public JLabel getTextField() {
        return textField;
    }

    public void setTextField(String text) {
        this.textField.setText(text);
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }
}