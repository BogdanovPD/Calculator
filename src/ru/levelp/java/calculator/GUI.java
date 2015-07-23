package ru.levelp.java.calculator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

public class GUI {

    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private GridLayout gridLayoutNumbers = new GridLayout(4, 3);
    private GridLayout gridLayoutOperators = new GridLayout(2, 2);
    private GridLayout gridLayoutLeftSide = new GridLayout(1, 3);
    private GridLayout gridLayoutMemory = new GridLayout(4, 1);
    private GridLayout gridLayoutTrig = new GridLayout(4, 1);
    private GridLayout gridLayoutOther = new GridLayout(4, 1);
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
    private JPanel panelMemory = new JPanel();
    private JPanel panelTrig = new JPanel();
    private JPanel panelOther = new JPanel();
    private JPanel panelLeftSide = new JPanel();
    private JLabel textField = new JLabel("0");
    private double current;
    private double lastCurrent;
    private double memory;
    private String operation = "";
    private String lastOperation = "";
    private boolean clear = false;
    private NumButtonListener NBL = new NumButtonListener(this);
    private OperationListener aL = new OperationListener(this);
    private JMenuBar bar = new JMenuBar();
    private JMenu menu = new JMenu("View");
    private JMenuItem menuItem1 = new JMenuItem("Standart");
    private JMenuItem menuItem2 = new JMenuItem("Extended");
    private int counter=0;

    public void build() {
        JFrame frame = new JFrame("Calculator");
        //frameOperators.add(panelOperators);
        //frameOperators.add(panelEquals);
        frame.setBounds(100, 100, 300, 174);
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
        panelMemory.setLayout(gridLayoutMemory);
        panelTrig.setLayout(gridLayoutTrig);
        panelOther.setLayout(gridLayoutOther);
        panelLeftSide.setLayout(gridLayoutLeftSide);
        Font font = getTextField().getFont();
        getTextField().setFont(new Font(font.getFontName(), font.getStyle(), font.getSize() + 10));
        textField.setHorizontalAlignment(JLabel.RIGHT);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        panelLeftSide.setVisible(false);
        //textField.setMinimumSize(new Dimension(100, 10));


        int j = 0;
        //789 456 123 = 012 345 678(ind)
        for (int i = 7; i < 10; i++) {
            buttons.add(new JButton(("" + i)));
            buttons.get(j).addActionListener(NBL);
            panel.add(buttons.get(j));
            j++;
        }

        for (int i = 4; i < 7; i++) {
            buttons.add(new JButton(("" + i)));
            buttons.get(j).addActionListener(NBL);
            panel.add(buttons.get(j));
            j++;
        }

        for (int i = 1; i < 4; i++) {
            buttons.add(new JButton(("" + i)));
            buttons.get(j).addActionListener(NBL);
            panel.add(buttons.get(j));
            j++;
        }

        buttons.add(new JButton(("0")));
        buttons.get(9).addActionListener(NBL);
        panel.add(buttons.get(9));

        buttons.add(new JButton(("+/-")));
        buttons.get(10).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextField("-" + textField.getText());
            }
        });
        panel.add(buttons.get(10));

        buttons.add(new JButton((",")));
        buttons.get(11).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().contains(".")) {
                    setTextField(textField.getText() + ".");
                }
            }
        });
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
                if (getTextField().getText().length() == 1) {
                    getTextField().setText("0");
                } else
                    getTextField().setText(getTextField().getText().substring(0, getTextField().getText().length() - 1));
            }
        });
        panelBackspace.add(buttons.get(18));

        buttons.add(new JButton(("MC")));
        buttons.get(19).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memory = 0;
            }
        });
        buttons.add(new JButton(("MR")));
        buttons.get(20).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mr = ("" + memory);
                setTextField(cutInsignificantZeros(mr));

            }
        });
        buttons.add(new JButton(("M-")));
        buttons.get(21).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memory = memory - Double.parseDouble(textField.getText());
            }
        });
        buttons.add(new JButton(("M+")));
        buttons.get(22).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memory = memory + Double.parseDouble(textField.getText());
            }
        });
        panelMemory.add(buttons.get(19));
        panelMemory.add(buttons.get(20));
        panelMemory.add(buttons.get(21));
        panelMemory.add(buttons.get(22));

        buttons.add(new JButton(("Sin")));
        buttons.get(23).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextField(cutInsignificantZeros(((Double) Math.sin(Double.parseDouble(textField.getText()))).toString()));
            }
        });
        buttons.add(new JButton(("Cos")));
        buttons.get(24).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextField(cutInsignificantZeros(((Double) Math.cos(Double.parseDouble(textField.getText()))).toString()));
            }
        });
        buttons.add(new JButton(("Tg")));
        buttons.get(25).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextField(cutInsignificantZeros(((Double) Math.tan(Double.parseDouble(textField.getText()))).toString()));
            }
        });
        buttons.add(new JButton(("Ctg")));
        buttons.get(26).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextField(cutInsignificantZeros(((Double) (1.0 / Math.tan(Double.parseDouble(textField.getText())))).toString()));
            }
        });
        buttons.add(new JButton(("Log")));
        buttons.get(27).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextField(cutInsignificantZeros(((Double) Math.log(Double.parseDouble(textField.getText()))).toString()));
            }
        });
        buttons.add(new JButton(("%")));
        buttons.get(28).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextField(cutInsignificantZeros(((Double) (current / 100.0 * Double.parseDouble(textField.getText()))).toString()));
            }
        });
        buttons.add(new JButton(("x^")));
        buttons.get(29).addActionListener(aL);
        buttons.add(new JButton(("sqrt")));
        buttons.get(30).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextField(cutInsignificantZeros(((Double) Math.sqrt(Double.parseDouble(textField.getText()))).toString()));
            }
        });

        panelTrig.add(buttons.get(23));
        panelTrig.add(buttons.get(24));
        panelTrig.add(buttons.get(25));
        panelTrig.add(buttons.get(26));

        panelOther.add(buttons.get(27));
        panelOther.add(buttons.get(28));
        panelOther.add(buttons.get(29));
        panelOther.add(buttons.get(30));

        panelLeftSide.add(panelOther);
        panelLeftSide.add(panelTrig);
        panelLeftSide.add(panelMemory);


        panelLabel.add(getTextField());

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelLeftSide.setVisible(false);
                frame.setBounds(100, 100, 300, 174);
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelLeftSide.setVisible(true);
                frame.setBounds(100, 100, 500, 174);
            }
        });

        menu.add(menuItem1);
        menu.add(menuItem2);
        bar.add(menu);
        frame.setJMenuBar(bar);

        frame.add(BorderLayout.CENTER, panel);

        panelOperatorsMain.add(BorderLayout.CENTER, panelOperators);
        panelOperatorsMain.add(BorderLayout.EAST, panelEquals);

        panelBackspaceC_Main.add(BorderLayout.WEST, panelC);
        panelBackspaceC_Main.add(BorderLayout.EAST, panelBackspace);

        panelBackspaceC_Operators_Main.add(BorderLayout.NORTH, panelBackspaceC_Main);
        panelBackspaceC_Operators_Main.add(BorderLayout.CENTER, panelOperatorsMain);

        panelRight.add(BorderLayout.NORTH, panelLabel);
        panelRight.add(BorderLayout.CENTER, panelBackspaceC_Operators_Main);

        frame.add(BorderLayout.EAST, panelRight);
        frame.add(BorderLayout.WEST, panelLeftSide);

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
        if (text.length() <= 11)
            this.textField.setText(text);
        else
            this.textField.setText(text.substring(0, 11));
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public String cutInsignificantZeros(String text) {
        return (text.endsWith(".0") ? text.substring(0, text.length() - 2) : text);
    }


    public String getLastOperation() {
        return lastOperation;
    }

    public void setLastOperation(String lastOperation) {
        this.lastOperation = lastOperation;
    }

    public double getLastCurrent() {
        return lastCurrent;
    }

    public void setLastCurrent(double lastCurrent) {
        this.lastCurrent = lastCurrent;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}