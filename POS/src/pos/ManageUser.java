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
    JFrame staffFrame;
    JPanel optionsPanel;
    JButton addUserButton;
    JButton removeUserButton;
    JButton viewStaffButton;
    JButton returnHomeButton;
    JTextField newUserID;
    JTextField newUserPassword;
    JTextField newUserName;
    JTextField newUserRole;
    JTextField removeID;
    JTable staffTable;
    
    public void go()
    {   
        //GUI Code
        manageUserFrame=new JFrame("POS System v 1.0");
        addUserButton=new JButton("Add New User");
        removeUserButton=new JButton("Remove a User");
        viewStaffButton=new JButton("View Staff");
        returnHomeButton=new JButton("Return Home");
        optionsPanel=new JPanel();
        returnHomeButton.addActionListener(new ReturnHomeListener());
        addUserButton.addActionListener(new AddUserListener());
        removeUserButton.addActionListener(new RemoveUserListener());
        viewStaffButton.addActionListener(new ViewStaffListener());
        
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
        optionsPanel.add(viewStaffButton,gbc);
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
        
        //View Staff GUI Code
        staffFrame=new JFrame("Staff");
        staffFrame.setResizable(false);
        staffFrame.setSize(800,800);
        staffFrame.setLocationRelativeTo(null);
        
        
        try{
            int rowCount=0;
            
            Connection conn=DriverManager.getConnection("jdbc:mysql://newkevinlindb.cul7akmhbeku.us-west-2.rds.amazonaws.com:3306/posUsers_DB","root","Restinpeace1");
            Statement st=conn.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT COUNT(*) FROM employees");
            
            while(rs.next())
            {
                rowCount=rs.getInt(1);
            }
            
            rs.close();
            
            staffTable=new JTable(rowCount+1,4);
            staffFrame.getContentPane().add(BorderLayout.CENTER,staffTable);
            
            JButton closeButton=new JButton("CLOSE");
            staffFrame.getContentPane().add(BorderLayout.SOUTH,closeButton);
            closeButton.addActionListener(new ActionListener()
                    {
                        @Override public void actionPerformed(ActionEvent ev)
                        {
                            staffFrame.dispose();
                            manageUserFrame.setVisible(true);
                        }
                    });
            
            staffTable.setValueAt("ID",0,0);
            staffTable.setValueAt("PASSWORD",0,1);
            staffTable.setValueAt("NAME",0,2);
            staffTable.setValueAt("POSITION",0,3);
            
            rs=st.executeQuery("Select * from employees");
            int row=1;
            int col=0;
            
            while(rs.next())
            {   
                    staffTable.setValueAt(rs.getString(1),row,0);
                    staffTable.setValueAt(rs.getString(2), row,1);
                    staffTable.setValueAt(rs.getString(3), row,2);
                    staffTable.setValueAt(rs.getString(4), row,3);
                    ++row;
                
            }
            
        }catch (SQLException ex)
        {
            Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
        }
        
        
        //New User GUI Code
        newUserFrame=new JFrame("New User");
        newUserFrame.setLayout(gridBagLayout);
        JButton submitToAdd=new JButton("Submit");
        JButton cancelAdd=new JButton("Cancel");
        submitToAdd.addActionListener(new SubmitAddListener());
        cancelAdd.addActionListener(new CancelAddListener());
        newUserName=new JTextField(20);
        newUserRole=new JTextField(20);
        newUserID=new JTextField(10);
        newUserName.setText("");
        JLabel newUserNameLabel=new JLabel("New User NM: ");
        newUserRole.setText("");
        JLabel newUserRoleLabel=new JLabel("New User RL: ");
        newUserID.setText("");
        JLabel newUserIDLabel=new JLabel("New User ID: ");
        newUserPassword=new JTextField(10);
        newUserPassword.setText("");
        JLabel newUserPasswordLabel=new JLabel("New User PW: ");
        gbc.gridx=0;
        gbc.gridy=0;
        newUserFrame.add(newUserNameLabel,gbc);
        gbc.gridx=1;
        newUserFrame.add(newUserName,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        newUserFrame.add(newUserRoleLabel,gbc);
        gbc.gridx=1;
        newUserFrame.add(newUserRole,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        newUserFrame.add(newUserIDLabel,gbc);
        gbc.gridx=1;
        newUserFrame.add(newUserID,gbc);
        gbc.gridx=0;
        gbc.gridy=3;
        newUserFrame.add(newUserPasswordLabel,gbc);
        gbc.gridx=1;
        newUserFrame.add(newUserPassword,gbc);
        gbc.gridy=4;
        gbc.gridx=0;
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
    
    public class CancelAddListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
            newUserName.setText("");
            newUserRole.setText("");
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
            
            if (newUserID.getText().equals("") || newUserPassword.getText().equals("") || newUserRole.getText().equals("") || newUserName.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"One or multiple fields are empty");
                return;
            }
            
            try
            {   
                boolean found=false;
                
                Connection conn=DriverManager.getConnection("jdbc:mysql://newkevinlindb.cul7akmhbeku.us-west-2.rds.amazonaws.com:3306/posUsers_DB","root","Restinpeace1");
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
                    newUserRole.setText("");
                    newUserName.setText("");
                    return;
                }
                else
                {
                    String sqlStatement="INSERT INTO `posUsers_DB`.`employees` (`id`, `password`, `name`, `position`) VALUES ('"+newUserID.getText()+"', '"+newUserPassword.getText()+"','"+newUserName.getText()+"','"+newUserRole.getText()+"')";
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
                manageUserFrame.dispose();
                ManageUser manageUser=new ManageUser();
                manageUser.go();
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
                
                Connection conn=DriverManager.getConnection("jdbc:mysql://newkevinlindb.cul7akmhbeku.us-west-2.rds.amazonaws.com:3306/posUsers_DB","root","Restinpeace1");
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
                    manageUserFrame.dispose();
                    ManageUser manageUser=new ManageUser();
                    manageUser.go();
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
    
    public class ViewStaffListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
            manageUserFrame.dispose();
            staffFrame.setVisible(true);
        }
    }
    
    public static void main (String args[])
    {
        ManageUser manageUser=new ManageUser();
        manageUser.go();
    }
}
