package ru.levelp.java.calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumButtonListener implements ActionListener {

    private GUI gui;

    public NumButtonListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = ((JButton)e.getSource()).getText();
        if (gui.getTextField().getText().equals("0")){
            gui.setTextField("");
        }
        if (!gui.getOperation().equals("") && !gui.isClear()){
            gui.setTextField("");
            gui.setClear(true);
        }
        gui.setTextField(gui.getTextField().getText() + text);
    }
}