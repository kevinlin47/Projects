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
public class ManageUser {
    
    JFrame manageUserFrame;
    JFrame newUserFrame;
    JFrame removeUserFrame;
    JPanel optionsPanel;
    JButton addUserButton;
    JButton removeUserButton;
    JButton cancelButton;
    JButton returnHomeButton;
    JTextField newUserID;
    JTextField newUserPassword;
    JTextField removeID;
    
    public void go()
    {   
        //GUI Code
        manageUserFrame=new JFrame("POS System v 1.0");
        addUserButton=new JButton("Add New User");
        removeUserButton=new JButton("Remove a User");
        cancelButton=new JButton("Cancel");
        returnHomeButton=new JButton("Return Home");
        optionsPanel=new JPanel();
        cancelButton.addActionListener(new CancelListener());
        returnHomeButton.addActionListener(new ReturnHomeListener());
        addUserButton.addActionListener(new AddUserListener());
        removeUserButton.addActionListener(new RemoveUserListener());
        
        GridBagLayout gridBagLayout=new GridBagLayout();
        optionsPanel.setLayout(gridBagLayout);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        optionsPanel.add(addUserButton,gbc);
        gbc.gridy=1;
        optionsPanel.add(Box.createVerticalStrut(12),gbc);
        gbc.gridy=2;
        optionsPanel.add(removeUserButton,gbc);
        gbc.gridy=3;
        optionsPanel.add(Box.createVerticalStrut(12),gbc);
        gbc.gridy=4;
        optionsPanel.add(cancelButton,gbc);
        gbc.gridy=5;
        optionsPanel.add(Box.createVerticalStrut(12),gbc);
        gbc.gridy=6;
        optionsPanel.add(returnHomeButton,gbc);
        
        
        manageUserFrame.setSize(800,800);
        manageUserFrame.getContentPane().add(BorderLayout.CENTER,optionsPanel);
        manageUserFrame.setLocationRelativeTo(null);
        manageUserFrame.setResizable(false);
        manageUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manageUserFrame.setVisible(true);
        
        //New User GUI Code
        newUserFrame=new JFrame("New User");
        newUserFrame.setLayout(gridBagLayout);
        JButton submitToAdd=new JButton("Submit");
        JButton cancelAdd=new JButton("Cancel");
        submitToAdd.addActionListener(new SubmitAddListener());
        cancelAdd.addActionListener(new CancelAddListener());
        newUserID=new JTextField(10);
        newUserID.setText("");
        JLabel newUserIDLabel=new JLabel("New User ID: ");
        newUserPassword=new JTextField(10);
        newUserPassword.setText("");
        JLabel newUserPasswordLabel=new JLabel("New User PW: ");
        gbc.gridx=0;
        gbc.gridy=0;
        newUserFrame.add(newUserIDLabel,gbc);
        gbc.gridx=1;
        newUserFrame.add(newUserID,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        newUserFrame.add(newUserPasswordLabel,gbc);
        gbc.gridx=1;
        newUserFrame.add(newUserPassword,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        newUserFrame.add(submitToAdd,gbc);
        gbc.gridx=1;
        newUserFrame.add(cancelAdd,gbc);
        
        newUserFrame.setSize(300,300);
        newUserFrame.setLocationRelativeTo(null);
        newUserFrame.setResizable(false);
        
        //Remove User GUI Code
        removeUserFrame=new JFrame("Remove User");
        removeUserFrame.setLayout(gridBagLayout);
        JButton submitToRemove=new JButton("Submit");
        JButton cancelRemove=new JButton("Cancel");
        submitToRemove.addActionListener(new SubmitRemoveListener());
        cancelRemove.addActionListener(new CancelRemoveListener());
        removeID=new JTextField(10);
        removeID.setText("");
        JLabel removeUserIDLabel=new JLabel("User ID(remove): ");
        gbc.gridx=0;
        gbc.gridy=0;
        removeUserFrame.add(removeUserIDLabel,gbc);
        gbc.gridx=1;
        removeUserFrame.add(removeID,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        removeUserFrame.add(submitToRemove,gbc);
        gbc.gridx=1;
        removeUserFrame.add(cancelRemove,gbc);
        
        removeUserFrame.setSize(300,300);
        removeUserFrame.setLocationRelativeTo(null);
        removeUserFrame.setResizable(false);
        
        
        
        
    }
    
    
    public class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            manageUserFrame.dispose();
            Manage m=new Manage();
            m.go();
        }
    }
    
    public class CancelAddListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
            newUserID.setText("");
            newUserPassword.setText("");
            newUserFrame.dispose();
        }
    }
    
    public class CancelRemoveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            removeID.setText("");
            removeUserFrame.dispose();
            
        }
    }
    
    public class SubmitAddListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
            boolean success=false;
            
            if (newUserID.getText().equals("") || newUserPassword.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"One or both fields is empty");
                return;
            }
            
            try
            {   
                boolean found=false;
                
                Connection conn=DriverManager.getConnection("jdbc:mysql://kevinlindb.cul7akmhbeku.us-west-2.rds.amazonaws.com:3306/posUsers_DB","root","password");
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("Select * from employees");
                
                while(rs.next())
                {
                    if (rs.getString(1).equals(newUserID.getText()))
                    {
                        found=true;
                    }
                }
                
                if (found==true)
                {
                    JOptionPane.showMessageDialog(null,"User ID Already in Use");
                    newUserFrame.dispose();
                    newUserID.setText("");
                    newUserPassword.setText("");
                    return;
                }
                else
                {
                    String sqlStatement="INSERT INTO `posUsers_DB`.`employees` (`id`, `password`) VALUES ('"+newUserID.getText()+"', '"+newUserPassword.getText()+"')";
                    st.executeUpdate(sqlStatement);
                    success=true;
                }
            } catch(SQLException ex)
            {   
                success=false;
                Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
            }
            
            if (success)
            {
                JOptionPane.showMessageDialog(null,"User Added Successfully");
                newUserFrame.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"User Added Unsuccessfully");
                newUserFrame.dispose();
            }
        }
    }
    
    public class SubmitRemoveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            
            if (removeID.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"No ID was entered");
                return;
            }
            
            try
            {   
                boolean found=false;
                
                Connection conn=DriverManager.getConnection("jdbc:mysql://kevinlindb.cul7akmhbeku.us-west-2.rds.amazonaws.com:3306/posUsers_DB","root","password");
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("Select * from employees");
                
                while(rs.next())
                {
                    if (rs.getString(1).equals(removeID.getText()))
                    {
                        found=true;
                    }
                }
                
                if (found==true)
                {   
                    String sqlStatement="DELETE FROM `posUsers_DB`.`employees` WHERE `id`='"+removeID.getText()+"'";
                    st.executeUpdate(sqlStatement);
                    JOptionPane.showMessageDialog(null,"User has been removed");
                    removeUserFrame.dispose();
                    removeID.setText("");
                    return;
                }
                else
                {   
                    JOptionPane.showMessageDialog(null,"ID not found");
                }
            } catch(SQLException ex)
            {   
                Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
            }
        }
        
    }
    
    public class ReturnHomeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            manageUserFrame.dispose();
            POS pos=new POS();
            pos.go();
        }
    }
    
    public class AddUserListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            newUserFrame.setVisible(true);
            newUserFrame.requestFocus();
        }
    }
    
    public class RemoveUserListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            removeUserFrame.setVisible(true);
            removeUserFrame.requestFocus();
        }
    }
    
    public static void main (String args[])
    {
        ManageUser manageUser=new ManageUser();
        manageUser.go();
    }
}
