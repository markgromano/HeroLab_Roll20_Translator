package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.XMLDocument;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.List;

public class frmMain {
    private JList listCharacters;
    private JPanel mainPanel;
    private JButton btnOpenXML;
    private JList listRolls;
    private JTextArea textRollOutput;
    private JButton copyTextButton;
    private JCheckBox checkBoxGMRoll;
    private JCheckBox checkCharge;
    private JCheckBox checkFlanking;
    private JCheckBox checkHighGround;
    private JButton buttonReload;
    private JCheckBox checkBane;
    private JCheckBox checkShowEquipped;
    private int selectedCharacterIndex = -1;
    private int selectedRollIndex = -1;

    private String xmlFilePath = "";
    private XMLDocument herolabOutput;

    public frmMain() {
        btnOpenXML.addActionListener(e -> chooseXMLFile());
        buttonReload.addActionListener(e -> fillCharacters());
        listCharacters.addListSelectionListener(this::characterSelect);
        listRolls.addListSelectionListener(this::rollSelect);
        copyTextButton.addActionListener(e -> copyToClipboard());
        checkBoxGMRoll.addActionListener(e -> generateRoll());
        checkCharge.addActionListener(e -> generateRoll());
        checkFlanking.addActionListener(e -> generateRoll());
        checkHighGround.addActionListener(e -> generateRoll());
        checkBane.addActionListener(e -> generateRoll());
        checkShowEquipped.addActionListener(e -> fillRolls());
    }
    private void characterSelect(ListSelectionEvent event) {
        if(!event.getValueIsAdjusting()){
            selectedCharacterIndex = listCharacters.getSelectedIndex();
            fillRolls();
        }
    }

    private void rollSelect(ListSelectionEvent event) {
        if(!event.getValueIsAdjusting()){
            selectedRollIndex = listRolls.getSelectedIndex();
            generateRoll();
        }
    }

    private void generateRoll() {
        if(herolabOutput != null && listRolls.getSelectedValue() != null){
            Character selectedCharacter = herolabOutput.getPublicElement().getCharacter().get(selectedCharacterIndex);
            List selectedRolls = listRolls.getSelectedValuesList();

            if(selectedRolls.contains(RollEntry.SPACER)){
                return;
            }

            CombatState combatState = new CombatState(checkCharge.isSelected(), checkFlanking.isSelected(),
                    checkHighGround.isSelected(), checkBane.isSelected());

            String rollOutput = OutputGenerator.getOutputBlock(selectedCharacter, selectedRolls, combatState);

            if(checkBoxGMRoll.isSelected()){
                rollOutput = "/w gm " + rollOutput;
            }

            textRollOutput.setText(rollOutput);
        }
    }

    private void fillRolls() {
        Object[] rolls = RollGenerator.getRolls(herolabOutput, selectedCharacterIndex, checkShowEquipped.isSelected());
        listRolls.setListData(rolls);
    }

    private void copyToClipboard() {
        StringSelection stringSelection = new StringSelection(textRollOutput.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        System.out.println("Output copied to clipboard.");
    }

    private void chooseXMLFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HeroLab Export File", "xml");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File("C:\\Temp"));
        int returnVal = chooser.showOpenDialog(mainPanel);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            xmlFilePath = chooser.getSelectedFile().getAbsolutePath();
            buttonReload.setEnabled(true);
            fillCharacters();
        }
    }

    private void fillCharacters() {
        herolabOutput = new XMLReader(xmlFilePath).read();
        System.out.println("XML read!");
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