package com.quizar.hrtranslator;

import javax.swing.*;

public class frmMain {
    private JList listCharacters;
    private JPanel mainPanel;

    public static void main(String args[]){
        JFrame frame = new JFrame("frmMain");
        frame.setContentPane(new frmMain().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}