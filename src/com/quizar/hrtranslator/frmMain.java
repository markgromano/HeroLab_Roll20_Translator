package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.Weapon;
import com.quizar.hrtranslator.herolab.XMLDocument;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

public class frmMain {
    private JList listCharacters;
    private JPanel mainPanel;
    private JButton btnOpenXML;
    private JList listRolls;
    private JTextArea textRollOutput;
    private int selectedCharacterIndex = -1;

    private String xmlFilePath = "";
    private XMLDocument herolabOutput;

    public frmMain() {
        btnOpenXML.addActionListener(e -> chooseXMLFile());
        listCharacters.addListSelectionListener(this::characterSelect);
    }

    private void characterSelect(ListSelectionEvent event) {
        if(!event.getValueIsAdjusting()){
            selectedCharacterIndex = listCharacters.getSelectedIndex();
            fillRolls();
        }
    }

    private void fillRolls() {
        if(herolabOutput != null){
            List<Weapon> weapons = herolabOutput.getPublicElement().getCharacter().get(selectedCharacterIndex)
                    .getMelee().getWeapon();
            if(weapons != null) {
                Object weaponRolls[] = weapons.stream().map(Weapon::getName).toArray();
                listRolls.setListData(weaponRolls);
            }else{
                listRolls.setListData(new Object[]{});
            }
        }
    }

    private void chooseXMLFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HeroLab Export File", "xml");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File("C:\\Temp"));
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