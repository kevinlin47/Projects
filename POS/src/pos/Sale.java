/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;


/**
 *
 * @author Kevin Lin
 */
public class Sale implements ActionListener{
    JFrame mainFrame;
    JLabel drinksLabel;
    JLabel sidesLabel;
    JLabel extrasLabel;
    JLabel sandwichLabel;
    JPanel salePanel;
    JPanel orderPanel;
    JPanel buttonPanel;
    JPanel chargePanel;
    JPanel numberPanel;
    JPanel tempPanel;
    JButton totalButton;
    JButton cancelButton;
    JButton removeButton;
    JList orderList;
    JLabel totalDisplay;
    JLabel amountEnteredLabel;
    JTextField enteredAmount;
    DefaultListModel dlm;
    ArrayList drinksList;
    ArrayList sidesList;
    ArrayList extrasList;
    HashMap itemPrices;
    double total;
    
    public void go ()
    {  
       drinksList=new ArrayList<String>();
       sidesList=new ArrayList<String>();
       extrasList=new ArrayList<String>();
       
       itemPrices=new HashMap();
       

        
        
        
       //GUI Code  
       dlm=new DefaultListModel();
       drinksLabel=new JLabel("Drinks");
       sidesLabel=new JLabel("Sides");
       extrasLabel=new JLabel("Extras");
       sandwichLabel=new JLabel("Sandwiches/etc");
       orderList=new JList(dlm);
       orderList.setVisible(true);
       orderList.setFixedCellWidth(125);
       orderList.setFixedCellHeight(25);
       orderList.setBackground(Color.WHITE);
       JScrollPane orderScrollPane=new JScrollPane(orderList);
       totalButton=new JButton("Total      ");
       cancelButton=new JButton("Cancel  ");
       removeButton=new JButton("Remove");
       orderPanel=new JPanel();
       int hgap=140;
       int vgap=0;
       FlowLayout flowLayout=new FlowLayout(FlowLayout.LEFT,hgap,vgap);
       BoxLayout boxLayout=new BoxLayout(orderPanel,BoxLayout.Y_AXIS);
       salePanel=new JPanel();
       salePanel.setLayout(flowLayout);
       salePanel.add(drinksLabel);
       salePanel.add(sidesLabel);
       salePanel.add(extrasLabel);
       salePanel.add(sandwichLabel);
       orderPanel.setLayout(boxLayout);
       orderPanel.add(totalButton);
       orderPanel.add(removeButton);
       orderPanel.add(cancelButton);
       cancelButton.addActionListener(new cancelListener());
       removeButton.addActionListener(new removeListener());
       totalButton.addActionListener(new totalListener());
       mainFrame=new JFrame("Sale");
       mainFrame.getContentPane().add(BorderLayout.NORTH,salePanel);
       mainFrame.getContentPane().add(BorderLayout.WEST,orderScrollPane);
       mainFrame.getContentPane().add(BorderLayout.SOUTH,orderPanel);
       mainFrame.setSize(800,800);
       mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       mainFrame.setLocationRelativeTo(null);
       buttonPanel=new JPanel();
       GridBagLayout gridBagLayout=new GridBagLayout();
       buttonPanel.setLayout(gridBagLayout);
       GridBagConstraints gbc=new GridBagConstraints();
       gbc.fill=GridBagConstraints.HORIZONTAL;
       gbc.anchor=GridBagConstraints.NORTHWEST;


       
               
       //Connect to MySql table for menu item information
       try{
        Connection conn=DriverManager.getConnection("jdbc:mysql://kevinlindb.cul7akmhbeku.us-west-2.rds.amazonaws.com:3306/posUsers_DB","root","password");
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from items");
        
        //Place items into respective arrays, and item prices into HashMap
        while(rs.next())
        {
            if (rs.getString(3).equals("drinks"))
            {
                drinksList.add(rs.getString(1));
                itemPrices.put(rs.getString(1),rs.getString(2));
            }
            else if (rs.getString(3).equals("sides"))
            {
                sidesList.add(rs.getString(1));
                itemPrices.put(rs.getString(1),rs.getString(2));
            }
            else
            {
                extrasList.add(rs.getString(1)); 
                itemPrices.put(rs.getString(1),rs.getString(2));
            }
        }
        
       } catch (SQLException ex)
       {
           Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
       }
       
       for (int i=0;i<drinksList.size();++i)
       {    
           gbc.gridx=0;
           gbc.gridy=i;
           JButton component=new JButton((String)drinksList.get(i));
           component.addActionListener(this);
           int top = 20;
           int left = 10;
           int bottom = 2;
           int right = 30;
           gbc.insets = new Insets(top, left, bottom, right);
           gridBagLayout.setConstraints(component, gbc);
           buttonPanel.add(component,gbc);
       }
       
       for (int i=0;i<sidesList.size();++i)
       {    
           gbc.gridx=1;
           gbc.gridy=i;
           JButton component=new JButton((String)sidesList.get(i));
           component.addActionListener(this);
           int top = 20;
           int left = 20;
           int bottom = 2;
           int right = 70;
           gbc.insets = new Insets(top,left,bottom,right);
           gridBagLayout.setConstraints(component,gbc);
           buttonPanel.add(component,gbc);
       }
       
       for (int i=0;i<extrasList.size();++i)
       {
           gbc.gridx=2;
           gbc.gridy=i;
           JButton component=new JButton((String)extrasList.get(i));
           component.addActionListener(this);
           int top = 20;
           int left = 40;
           int bottom = 2;
           int right = 60;
           gbc.insets = new Insets(top,left,bottom,right);
           gridBagLayout.setConstraints(component,gbc);
           buttonPanel.add(component,gbc);

       }
       
       JButton sandwichButton=new JButton("Sandwich");
       JButton saladButton=new JButton("Salad");
       int top = 20;
       int left = 30;
       int bottom = 2;
       int right = 20;
       gbc.insets = new Insets(top,left,bottom,right);
       gridBagLayout.setConstraints(sandwichButton,gbc);
       gbc.gridx=3;
       gbc.gridy=0;
       buttonPanel.add(sandwichButton,gbc);
       gbc.gridy=1;
       gridBagLayout.setConstraints(saladButton,gbc);
       buttonPanel.add(saladButton,gbc);
       gbc.weightx=.25;
       gbc.weighty=.25;
       buttonPanel.add(new JLabel(" "),gbc);
       mainFrame.getContentPane().add(BorderLayout.CENTER,buttonPanel);
       mainFrame.setResizable(false);
       mainFrame.setVisible(true);
       
       //Create Transaction Panel
                  /*int top = 20;
           int left = 10;
           int bottom = 2;
           int right = 30;
           gbc.insets = new Insets(top, left, bottom, right);
           gridBagLayout.setConstraints(component, gbc);
           buttonPanel.add(component,gbc);*/
       chargePanel=new JPanel();
       chargePanel.setLayout(gridBagLayout);
       totalDisplay=new JLabel("Test");
       totalDisplay.setFont(new Font("Courier New", Font.PLAIN, 32));
       totalDisplay.setOpaque(true);
       totalDisplay.setForeground(Color.GREEN);
       totalDisplay.setBackground(Color.BLACK);
       JLabel totalLabel=new JLabel("Total: ");
       totalLabel.setFont(new Font("Courier New",Font.PLAIN,28));
       chargePanel.add(totalLabel,gbc);
       chargePanel.add(totalDisplay,gbc);
       amountEnteredLabel=new JLabel("Amount: ");
       amountEnteredLabel.setFont(new Font("Courier New",Font.PLAIN,28));
       chargePanel.add(amountEnteredLabel,gbc);
       
       
       JButton chargeCancel=new JButton("Cancel");
       chargeCancel.addActionListener(new ChargeCancelListener());
       numberPanel=new JPanel();
       numberPanel.add(chargeCancel);
       

       

       
       
       
       

    }
    
    public void actionPerformed(ActionEvent ev)
    {
        String item=ev.getActionCommand();
        String itemPrice=(String)itemPrices.get(item);
        dlm.addElement(item+"-"+itemPrice);
        orderList.setModel(dlm);
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
    
    public class ChargeCancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
            mainFrame.remove(chargePanel);
            mainFrame.remove(numberPanel);
            mainFrame.remove(tempPanel);
            mainFrame.getContentPane().add(BorderLayout.SOUTH,orderPanel);
            mainFrame.getContentPane().add(BorderLayout.NORTH,salePanel);
            mainFrame.getContentPane().add(BorderLayout.CENTER,buttonPanel);
            mainFrame.repaint();
            mainFrame.revalidate();
        }
    }
    
    public class removeListener implements ActionListener
    {   
        public void actionPerformed(ActionEvent ev)
        {
            dlm.removeElement(orderList.getSelectedValue());
            orderList.setModel(dlm);
        }
    }
    
    public class totalListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
            Thread a=new Thread(new CalculateTotal());
            mainFrame.remove(orderPanel);
            mainFrame.remove(salePanel);
            mainFrame.remove(buttonPanel);
            mainFrame.getContentPane().add(BorderLayout.NORTH,chargePanel);
            tempPanel=new JPanel();
            tempPanel.setLayout(new BoxLayout(tempPanel,BoxLayout.Y_AXIS));
            tempPanel.add(Box.createVerticalStrut(80));
            mainFrame.getContentPane().add(BorderLayout.SOUTH,tempPanel);
            mainFrame.getContentPane().add(BorderLayout.CENTER,numberPanel);
            mainFrame.repaint();
            mainFrame.revalidate();
            a.start(); //run thread to calculate total amount to display 

        }
    }
    
    //Class for calculating the total price
    public class CalculateTotal implements Runnable
    {
        public void run()
        {   
            ListModel model=orderList.getModel();
            int numItems=orderList.getModel().getSize();
            total=0;
            
            
            for (int i=0;i<numItems;++i)
            {   
                String temp=(String)model.getElementAt(i);
                String []itemAndPrice=temp.split("-");
                double temp2=Double.parseDouble((String)itemPrices.get(itemAndPrice[0]));
                total=total+temp2;
            }
            
            String amountToDisplay=String.format("%.2f",total);
            totalDisplay.setText(amountToDisplay);
            
        }
    }
    
    public static void main (String [] args)
    {
        Sale sale=new Sale();
        sale.go();
    }

    
    
}
