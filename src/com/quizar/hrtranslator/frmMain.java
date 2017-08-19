package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.XMLDocument;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMain {
    private JList listCharacters;
    private JPanel mainPanel;
    private JButton btnOpenXML;

    private String xmlFilePath = "";
    private XMLDocument herolabOutput;

    public frmMain() {
        btnOpenXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseXMLFile();
            }
        });
    }

    private void chooseXMLFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "HeroLab Export File", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(mainPanel);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            xmlFilePath = chooser.getSelectedFile().getAbsolutePath();
            herolabOutput = new XMLReader(xmlFilePath).read();
            System.out.println("XML read!");
            fillCharacters();
        }
    }

    private void fillCharacters() {
        if(herolabOutput != null){
            Object names[] = herolabOutput.getPublicElement().getCharacter().stream().map(Character::getName).toArray();
            listCharacters.setListData(names);
        }
    }

    public static void main(String args[]){
        JFrame frame = new JFrame("HeroLab to Roll20 Translator");
        frame.setContentPane(new frmMain().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}