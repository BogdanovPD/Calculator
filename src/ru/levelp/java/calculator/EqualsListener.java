package ru.levelp.java.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EqualsListener implements ActionListener {

    GUI gui;

    public EqualsListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gui.getOperation().equals("+")){
            double labelText = Double.parseDouble(gui.getTextField().getText());
            String newText = ""+(labelText+gui.getCurrent());
            gui.setTextField(""+(labelText+gui.getCurrent()));
            gui.setTextField(newText.endsWith(".0")? newText=newText.substring(0, newText.length()-2):newText);
            gui.setOperation("=");
            gui.setClear(false);
        }
        if (gui.getOperation().equals("-")){
            double labelText = Double.parseDouble(gui.getTextField().getText()+".0");
            gui.setTextField(""+(gui.getCurrent()-labelText));
            gui.setOperation("=");
            gui.setClear(false);
        }
        if (gui.getOperation().equals("*")){
            double labelText = Double.parseDouble(gui.getTextField().getText()+".0");
            gui.setTextField(""+(labelText*gui.getCurrent()));
            gui.setOperation("=");
            gui.setClear(false);
        }
        if (gui.getOperation().equals("/")){
            double labelText = Double.parseDouble(gui.getTextField().getText()+".0");
            gui.setTextField(""+(gui.getCurrent()/labelText));
            gui.setOperation("=");
            gui.setClear(false);
        }
    }
}
