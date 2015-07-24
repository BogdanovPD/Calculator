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
        useOperation(((JButton) e.getSource()).getText(), e);
    }


    public void useOperation(String op, ActionEvent e) {

//            if ((!gui.getLastOperation().equals("=")) && (!gui.getLastOperation().equals("")))
//                gui.setOperation(op);

            if (gui.getOperation().equals("=") ){
                if (gui.getCounter()>0 && op.equals("=")){
                    gui.setOperation(gui.getLastOperation());
                    gui.setLastOperation("=");}
                else
                    gui.setOperation("");
            }
            else {
                gui.setCounter(0);
            }

            if ((!gui.getOperation().equals("") && gui.isPushedNum()) || gui.getLastOperation().equals("=")) {
                String newText = "";
                double labelText;
                if ((!op.equals("=")) || (gui.getCounter()==0))
                    labelText = Double.parseDouble(gui.getTextField().getText());
                else{
                    labelText = gui.getLastCurrent();
                    gui.setCounter(gui.getCounter()+1);}
                if (gui.getOperation().equals("+"))
                    newText = "" + (labelText + gui.getCurrent());
                if (gui.getOperation().equals("*"))
                    newText = "" + (labelText * gui.getCurrent());
                if (gui.getOperation().equals("-"))
                    newText = "" + (gui.getCurrent() - labelText);
                if (gui.getOperation().equals("/"))
                    newText = "" + (gui.getCurrent() / labelText);
                if (gui.getOperation().equals("x^"))
                    newText = "" + (Math.pow(gui.getCurrent(),labelText));
                if (gui.getCounter()==0)
                    gui.setLastCurrent(labelText);
                gui.setLastOperation(gui.getOperation());
                gui.setTextField(newText);
                gui.setTextField(gui.cutInsignificantZeros(newText));
                gui.setOperation("=");
                gui.setClear(false);
            }
            gui.setCurrent(Double.parseDouble(gui.getTextField().getText()));

            if (op.equals("+")){
                gui.setOperation("+");}
            if (op.equals("*")){
                gui.setOperation("*");}
            if (op.equals("-")){
                gui.setOperation("-");}
            if (op.equals("/")){
                gui.setOperation("/");}
            if (op.equals("x^")){
                gui.setOperation("x^");}
            if (op.equals("="))
                gui.setCounter(gui.getCounter()+1);
            gui.setPushedNum(false);
            gui.setClear(false);

    }
}