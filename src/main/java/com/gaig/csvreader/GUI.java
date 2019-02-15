/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaig.csvreader;

import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author cjohannemann
 */
public class GUI extends JFrame {

    final int WIDTH = 450;
    final int HEIGHT = 150;
    private String savePath = null;
    JButton menuButton, submit, exit;
    JLabel label;
    JTextField selectedPath;
    JFileChooser choosey;

    GUI() {
        createMasterPanel();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("CSV Parser");
        setVisible(true);
    }

    public void createMasterPanel() {
        JPanel controlPanel = selectFile();
        JPanel submitPanel = submitFile();

        JPanel panel = new JPanel();
        panel.add(controlPanel);
        panel.add(submitPanel);
        add(panel, BorderLayout.SOUTH);
        JPanel panel2 = new JPanel();
        panel2.add(controlPanel);
        add(panel2, BorderLayout.CENTER);
    }

    private JPanel selectFile() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Select Excel Sheet");
        menuButton = new JButton("...");
        selectedPath = new JTextField(25);
        selectedPath.setEditable(false);
        choosey = new JFileChooser("C://");
        choosey.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        menuButton.addActionListener((ae) -> {
            int returnValues = choosey.showSaveDialog(null);
            if (returnValues == JFileChooser.APPROVE_OPTION) {
                if (choosey.getSelectedFile().isFile()) {
                    System.out.println("You have selected: " + choosey.getSelectedFile());
                    selectedPath.setText(choosey.getSelectedFile().getAbsolutePath());
                    savePath = choosey.getSelectedFile().getAbsolutePath();
                } else {
                    System.out.println("No Selection ");
                }
                choosey.setAcceptAllFileFilterUsed(false);
            }
        });
        panel.add(label);
        panel.add(menuButton);
        panel.add(selectedPath);
        return panel;
    }

    private JPanel submitFile() {
        submit = new JButton("Submit");
        exit = new JButton("Exit");
        JPanel panel = new JPanel();
        panel.add(submit);
        panel.add(exit);
        submit.addActionListener((ae) -> {
            try{
                CSVParser.parsing(savePath);
            }catch(Exception ex){
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        exit.addActionListener((ae) -> {
            System.exit(0);
        });
        return panel;
    }
    
    

}
