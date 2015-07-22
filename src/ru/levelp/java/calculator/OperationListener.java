package ru.levelp.java.calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationListener implements ActionListener {

    private GUI gui;

    public OperationListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (((JButton) e.getSource()).getText().equals("+")) {
//            if (gui.getOperation().equals("+")) {
//                double labelText = Double.parseDouble(gui.getTextField().getText());
//                String newText = "" + (labelText + gui.getCurrent());
//                gui.setTextField("" + (labelText + gui.getCurrent()));
//                gui.setTextField(newText.endsWith(".0") ? newText = newText.substring(0, newText.length() - 2) : newText);
//                gui.setOperation("");
//                gui.setClear(false);
//            }
//            gui.setCurrent(Double.parseDouble(gui.getTextField().getText()));
//            gui.setClear(false);
//            gui.setOperation("+");
//        }

        useOperation(((JButton) e.getSource()).getText(), e);

    }


    public void useOperation(String op, ActionEvent e) {

            if (gui.getOperation().equals("=")){
                gui.setOperation("");
            }

            if (!gui.getOperation().equals("")) {
                String newText = "";
                double labelText = Double.parseDouble(gui.getTextField().getText());
                if (gui.getOperation().equals("+"))
                    newText = "" + (labelText + gui.getCurrent());
                if (gui.getOperation().equals("*"))
                    newText = "" + (labelText * gui.getCurrent());
                if (gui.getOperation().equals("-"))
                    newText = "" + (gui.getCurrent() - labelText);
                if (gui.getOperation().equals("/"))
                    newText = "" + (gui.getCurrent() / labelText);
                gui.setTextField(newText);
                gui.setTextField(newText.endsWith(".0") ? newText = newText.substring(0, newText.length() - 2) : newText);
                gui.setOperation("=");
                gui.setClear(false);
            }
            gui.setCurrent(Double.parseDouble(gui.getTextField().getText()));

            if (op.equals("+"))
                gui.setOperation("+");
            if (op.equals("*"))
                gui.setOperation("*");
            if (op.equals("-"))
                gui.setOperation("-");
            if (op.equals("/"))
                gui.setOperation("/");
            gui.setClear(false);
    }
}