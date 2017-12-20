/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Kevin Lin
 */
public class Manage {
    
    JFrame mainFrame;
    JPanel optionsPanel;
    JButton editItemButton;
    JButton cancelButton;
    JButton editUserButton;
    
    public void go()
    {   
        //GUI Code
        mainFrame=new JFrame("Manage");
        optionsPanel=new JPanel();
        editItemButton=new JButton("Edit Menu");
        editUserButton=new JButton("Edit Users");
        cancelButton=new JButton("   Cancel   ");
        cancelButton.addActionListener(new cancelListener());
        editItemButton.addActionListener(new editItemListener());
        editUserButton.addActionListener(new editUserListener());
        
        BoxLayout boxLayout=new BoxLayout(optionsPanel,BoxLayout.Y_AXIS);
        optionsPanel.setLayout(boxLayout);
        optionsPanel.add(editItemButton);
        optionsPanel.add(Box.createVerticalStrut(10));
        optionsPanel.add(editUserButton);
        optionsPanel.add(Box.createVerticalStrut(10));
        optionsPanel.add(cancelButton);
        mainFrame.setSize(800,800);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.getContentPane().add(BorderLayout.WEST,optionsPanel);
        mainFrame.setVisible(true);
        
        
    }
    
    
    public class cancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            mainFrame.dispose();
            POS pos=new POS();
            pos.go();
        }
    }
    
    public class editItemListener implements ActionListener
    {   
        public void actionPerformed(ActionEvent ev)
        {
            mainFrame.dispose();
            ManageItem mI=new ManageItem();
            mI.go();
        }
    }
    
    public class editUserListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            mainFrame.dispose();
            ManageUser mU=new ManageUser();
            mU.go();
        }
    }
    
    public static void main(String args[])
    {
        Manage manage=new Manage();
        manage.go();
    }
}
