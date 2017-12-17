/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Kevin Lin
 */
public class POS {

    JFrame mainFrame;
    JFrame loginForm;
    JFrame mLoginForm;
    JButton saleButton;
    JButton manageButton;
    JButton exitButton;
    JButton okButton;
    JButton mOkButton;
    JButton cancelButton;
    JButton mCancelButton;
    JTextField id;
    JTextField code;
    JTextField mID;
    JTextField mCode;
    
    public void go()
    {   
        //GUI Code
        mainFrame=new JFrame("POS System v 1.0");
        saleButton=new JButton("Sale");
        manageButton=new JButton("Manage");
        exitButton=new JButton("Exit");
        
        BoxLayout boxLayout=new BoxLayout(mainFrame.getContentPane(),BoxLayout.Y_AXIS); 
        
        mainFrame.add(saleButton);
        saleButton.addActionListener(new saleListener());
        mainFrame.add(Box.createVerticalStrut(10));
        mainFrame.add(manageButton);
        mainFrame.add(Box.createVerticalStrut(10));
        mainFrame.add(exitButton);
        manageButton.addActionListener(new manageListener());
        exitButton.addActionListener(new ExitListener());
        mainFrame.setLayout(boxLayout);
        mainFrame.setSize(800,800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        
        loginForm=new JFrame("Login");
        JLabel idLabel=new JLabel("ID: ");
        JLabel passLabel=new JLabel("Password: ");
        id=new JTextField(10);
        code=new JTextField(10);
        okButton=new JButton("OK");
        cancelButton=new JButton("CANCEL");
        cancelButton.addActionListener(new cancelListener());
        okButton.addActionListener(new okListener());
        loginForm.getRootPane().setDefaultButton(okButton);
        
        GridBagLayout gridBagLayout=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        loginForm.setLayout(gridBagLayout);
        loginForm.add(idLabel,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        loginForm.add(id,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        loginForm.add(passLabel,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        loginForm.add(code,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        loginForm.add(okButton,gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        loginForm.add(cancelButton,gbc);
        loginForm.setSize(300,300);
        loginForm.setLocationRelativeTo(null);
        
        mLoginForm=new JFrame("Login");
        mID=new JTextField(10);
        mCode=new JTextField(10);
        idLabel=new JLabel("ID: ");
        passLabel=new JLabel("Password: ");
        mOkButton=new JButton("OK");
        mCancelButton=new JButton("CANCEL");
        mCancelButton.addActionListener(new ManageCancelListener());
        mOkButton.addActionListener(new ManageOkListener());
        mLoginForm.getRootPane().setDefaultButton(okButton);
        
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        mLoginForm.setLayout(gridBagLayout);
        mLoginForm.add(idLabel,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        mLoginForm.add(mID,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        mLoginForm.add(passLabel,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        mLoginForm.add(mCode,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        mLoginForm.add(mOkButton,gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        mLoginForm.add(mCancelButton,gbc);
        mLoginForm.setSize(300,300);
        mLoginForm.setLocationRelativeTo(null);
        
    }
    
    public static void main(String[] args) {
        POS start=new POS();
        start.go();
    }
    
    public class saleListener implements ActionListener
    {   
        public void actionPerformed(ActionEvent ev)
        {   
            loginForm.setVisible(true);
            loginForm.requestFocus();
        }
    }
    
    public class manageListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            mLoginForm.setVisible(true);
            mLoginForm.requestFocus();
        }    
    }
    
    public class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            mainFrame.dispose();
        }
    }
    
    public class cancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
            id.setText("");
            code.setText("");
            loginForm.dispose();
        }
    }
    
    public class okListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            String userID=id.getText();
            String passWord=code.getText();
            
            boolean success=false;
            
            try{
            //Connect to MySql database table
            Connection conn=DriverManager.getConnection("jdbc:mysql://newkevinlindb.cul7akmhbeku.us-west-2.rds.amazonaws.com:3306/posUsers_DB","root","Restinpeace1");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("select * from employees");
            
            //Search the table for the entered login information
            while (rs.next())
            {
                if (rs.getString(1).equals(userID) && rs.getString(2).equals(passWord))
                {
                    success=true;
                    break;
                }
            }
            
            //if the id and password combination is found
            if (success)
            {
                Sale sale=new Sale();
                sale.go();
                loginForm.dispose();
                mainFrame.dispose();
            }
            //if the id and password combination is not found
            else
            {
                JOptionPane.showMessageDialog(null,"Invlaid id and password");
            }
            
            } catch (SQLException ex)
            {
                Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
            }

            
        }
    }
    
    public class ManageOkListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            try{
                Connection conn=DriverManager.getConnection("jdbc:mysql://newkevinlindb.cul7akmhbeku.us-west-2.rds.amazonaws.com:3306/posUsers_DB","root","Restinpeace1");
                PreparedStatement st=conn.prepareStatement("Select * from employees where id=? and password=? and (position=? or position=?)");
                
                String enteredPW=mCode.getText();
                String enteredID=mID.getText();
                st.setString(1,enteredID);
                st.setString(2,enteredPW);
                st.setString(3,"Manager");
                st.setString(4,"Owner");
                
                ResultSet rs=st.executeQuery();
                
                if(!rs.next())
                {
                    JOptionPane.showMessageDialog(null,"You do not have permission to access this feature");
                }
                else
                {   
                    mLoginForm.dispose();
                    mainFrame.dispose();
                    Manage manage=new Manage();
                    manage.go();
                }
                
            }catch(SQLException ex)
            {
                Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
            }
        }
    }
    
    public class ManageCancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            mID.setText("");
            mCode.setText("");
            mLoginForm.dispose();
        }
    }
}
