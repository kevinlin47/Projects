/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.*;



/**
 *
 * @author Kevin Lin
 */
public class ManageItem {
    
    JFrame itemFrame;
    JPanel optionsPanel;
    JButton addButton;
    JButton removeButton;
    JButton cancelButton;
    JButton returnHomeButton;
    JFrame addItemFrame;
    GridBagLayout gridBagLayout;
    JTextField itemNameField;
    JTextField itemPriceField;
    JTextField itemCategoryField;
    JLabel itemLabel;
    JLabel priceLabel;
    JLabel categoryLabel;
    JFrame removeItemFrame;
    JLabel itemToRemoveLabel;
    JTextField itemToRemoveField;
    
    
            
    public void go()
    {   
        //GUI Code
        itemFrame=new JFrame("POS System v 1.0");
        optionsPanel=new JPanel();
        GridBagLayout gbl=new GridBagLayout();
        optionsPanel.setLayout(gbl);
        addButton=new JButton("Add New Item");
        removeButton=new JButton("Remove an Item");
        cancelButton=new JButton("Cancel");
        returnHomeButton=new JButton("Return Home");
        returnHomeButton.addActionListener(new ReturnButton());
        cancelButton.addActionListener(new CancelListener());
        addButton.addActionListener(new AddItemListener());
        removeButton.addActionListener(new RemoveItemListener());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=0;
        gbc.gridy=0;
        optionsPanel.add(addButton,gbc);
        gbc.gridy=1;
        optionsPanel.add(Box.createVerticalStrut(12),gbc);
        gbc.gridy=5;
        optionsPanel.add(removeButton,gbc);
        gbc.gridy=6;
        optionsPanel.add(Box.createVerticalStrut(12),gbc);
        gbc.gridy=10;
        optionsPanel.add(cancelButton,gbc);
        gbc.gridy=11;
        optionsPanel.add(Box.createVerticalStrut(12),gbc);
        gbc.gridy=15;
        optionsPanel.add(returnHomeButton,gbc);
        
        addItemFrame=new JFrame("New Item");
        gridBagLayout=new GridBagLayout();
        addItemFrame.setLayout(gridBagLayout);
        itemNameField=new JTextField(10);
        itemNameField.setText("");
        itemPriceField=new JTextField(10);
        itemPriceField.setText("");
        itemCategoryField=new JTextField(10);
        itemCategoryField.setText("");
        itemLabel=new JLabel("Item Name: ");
        priceLabel=new JLabel("Item Price: ");
        categoryLabel=new JLabel("Item Category: ");
        gbc.gridx=0;
        gbc.gridy=0;
        addItemFrame.add(itemLabel,gbc);
        gbc.gridx=1;
        addItemFrame.add(itemNameField,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        addItemFrame.add(priceLabel,gbc);
        gbc.gridx=1;
        addItemFrame.add(itemPriceField,gbc);
        JButton submitButton=new JButton("Submit");
        JButton addItemCancelButton=new JButton("Cancel");
        addItemCancelButton.addActionListener(new AddItemCancelListener());
        submitButton.addActionListener(new SubmitActionListener());
        gbc.gridx=0;
        gbc.gridy=2;
        addItemFrame.add(categoryLabel,gbc);
        addItemFrame.add(submitButton,gbc);
        gbc.gridx=1;
        addItemFrame.add(itemCategoryField,gbc);
        gbc.gridx=0;
        gbc.gridy=3;
        addItemFrame.add(submitButton,gbc);
        gbc.gridx=1;
        addItemFrame.add(addItemCancelButton,gbc);
        addItemFrame.setSize(300,300);
        addItemFrame.setLocationRelativeTo(null);
        addItemFrame.setResizable(false);
        
        removeItemFrame=new JFrame("Remove Item");
        removeItemFrame.setLayout(gridBagLayout);
        JButton removeSubmitButton=new JButton("Submit");
        JButton removeCancelButton=new JButton("Cancel");
        removeSubmitButton.addActionListener(new RemoveSubmitListener());
        removeCancelButton.addActionListener(new RemoveCancelListener());
        itemToRemoveField=new JTextField(10);
        itemToRemoveField.setText("");
        itemToRemoveLabel=new JLabel("Item Name(remove): ");
        gbc.gridx=0;
        gbc.gridy=0;
        removeItemFrame.add(itemToRemoveLabel,gbc);
        gbc.gridx=1;
        removeItemFrame.add(itemToRemoveField,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        removeItemFrame.add(removeSubmitButton,gbc);
        gbc.gridx=1;
        removeItemFrame.add(removeCancelButton,gbc);
        removeItemFrame.setSize(300,300);
        removeItemFrame.setLocationRelativeTo(null);
        removeItemFrame.setResizable(false);
        
        itemFrame.getContentPane().add(BorderLayout.CENTER,optionsPanel);
        itemFrame.setResizable(false);
        itemFrame.setSize(800,800);
        itemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        itemFrame.setLocationRelativeTo(null);
        itemFrame.setVisible(true);
    }
    
    public class SubmitActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            boolean success=false;
            
            try{
            String itemName=itemNameField.getText();
            String itemPrice=itemPriceField.getText();
            String itemCategory=itemCategoryField.getText();
            
            if (itemName.equals("") || itemPrice.equals("") || itemCategory.equals(""))
            {
                JOptionPane.showMessageDialog(null,"One or more fields are empty");
                return;
            }
            else
            {   
            
               Connection conn=DriverManager.getConnection("jdbc:mysql://newkevinlindb.cul7akmhbeku.us-west-2.rds.amazonaws.com:3306/posUsers_DB","root","Restinpeace1");
               Statement st=conn.createStatement();
            
               String sqlStatment="INSERT INTO `posUsers_DB`.`items` (`itemname`, `itemprice`, `itemtype`) VALUES ('"+itemName+"', '"+itemPrice+"', '"+itemCategory+"')";
               st.executeUpdate(sqlStatment);
               success=true;
            }
            } catch (SQLException ex)
            {   
                Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
            }
            
            if (success==true)
            {
                JOptionPane.showMessageDialog(null,"Item added successfully");
                addItemFrame.dispose();
                itemNameField.setText("");
                itemPriceField.setText("");
                itemCategoryField.setText("");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Failed adding item");
            }
        }
    }
    
    public class AddItemCancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
            itemNameField.setText("");
            itemPriceField.setText("");
            itemCategoryField.setText("");
            addItemFrame.dispose();
        }
    }
    
    public class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            itemFrame.dispose();
            Manage manage=new Manage();
            manage.go();
        }
    }
    
    public class ReturnButton implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            itemFrame.dispose();
            POS pos=new POS();
            pos.go();
        }
    }
    
    public class AddItemListener implements ActionListener
    {   
        public void actionPerformed(ActionEvent ev)
        {
            addItemFrame.setVisible(true);
            addItemFrame.requestFocus();
        }
                

    }

    public class RemoveSubmitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
           boolean success=false;
           try{
               String itemToDelete=itemToRemoveField.getText();
               
               if (itemToDelete.equals(""))
               {
                   JOptionPane.showMessageDialog(null,"Item Name Field Empty");
                   return;
               }
               else
               {    
                   boolean found=false;
                    Connection conn=DriverManager.getConnection("jdbc:mysql://newkevinlindb.cul7akmhbeku.us-west-2.rds.amazonaws.com:3306/posUsers_DB","root","Restinpeace1");
                    Statement st=conn.createStatement();
                    ResultSet rs=st.executeQuery("Select * from items");
                    
                    while(rs.next())
                    {
                        if (rs.getString(1).equals(itemToDelete))
                        {
                            found=true;
                            break;
                        }
                    }
                    
                    if (found==true)
                    {
                        String sqlStatement="DELETE FROM `posUsers_DB`.`items` WHERE `itemname`='"+itemToDelete+"'";
                        st.executeUpdate(sqlStatement);   
                        success=true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Item was not found");
                    }
               }
           } catch (SQLException ex)
           {
               Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
           }
           
           if (success==true)
           {
               JOptionPane.showMessageDialog(null,"Item Removed Successfully");
               removeItemFrame.dispose();
               itemToRemoveField.setText("");
           }
           else
           {
               JOptionPane.showMessageDialog(null,"Item Removal Failed");
           }
        }
    }
    
    public class RemoveCancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
            removeItemFrame.dispose();  
            itemToRemoveField.setText("");
        }
    }
    
    public class RemoveItemListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            removeItemFrame.setVisible(true);
            removeItemFrame.requestFocus();
        }
    }
    
    public static void main(String [] args)
    {
        ManageItem manageItem=new ManageItem();
        manageItem.go();
    }
    
}
