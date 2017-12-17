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
import java.awt.event.WindowEvent;
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
import javax.swing.ListModel;


/**
 *
 * @author Kevin Lin
 */
public class Sale implements ActionListener{
    JFrame mainFrame;
    JFrame changeFrame;
    JLabel drinksLabel;
    JLabel sidesLabel;
    JLabel extrasLabel;
    JLabel sandwichLabel;
    JLabel changeDisplay;
    JLabel changeLabel;
    JPanel salePanel;
    JPanel orderPanel;
    JPanel buttonPanel;
    JPanel chargePanel;
    JPanel numberPanel;
    JPanel tempPanel;
    JPanel changePanel;
    JPanel sandwichPanel;
    JButton totalButton;
    JButton cancelButton;
    JButton chargeCancel;
    JButton removeButton;
    JButton exactButton;
    JButton ceilButton;
    JButton fiveButton;
    JButton tenButton;
    JButton twentyButton;
    JButton backButton;
    JButton clearButton;
    JButton cashButton;
    JList orderList;
    JLabel totalDisplay;
    JLabel enteredAmount;
    DefaultListModel dlm;
    ArrayList drinksList;
    ArrayList sidesList;
    ArrayList extrasList;
    ArrayList sandwichList;
    HashMap itemPrices;
    Thread a;
    Thread b;
    double total;
    String [] amount=new String [10];
    boolean saladActive=false;
    volatile boolean shutdown=false;
    volatile boolean transactionComplete=false;
    
    public void go ()
    {  
       drinksList=new ArrayList<String>();
       sidesList=new ArrayList<String>();
       extrasList=new ArrayList<String>();
       sandwichList=new ArrayList<String>();
       
       itemPrices=new HashMap();


        
        
        
       //GUI Code  
       dlm=new DefaultListModel();
       drinksLabel=new JLabel("Drinks");
       sidesLabel=new JLabel("Sides");
       extrasLabel=new JLabel("Extras");
       sandwichLabel=new JLabel("Sandwiches/etc");
       orderList=new JList(dlm);
       orderList.setVisible(true);
       orderList.setFixedCellWidth(140);
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
        Connection conn=DriverManager.getConnection("jdbc:mysql://newkevinlindb.cul7akmhbeku.us-west-2.rds.amazonaws.com:3306/posUsers_DB","root","Restinpeace1");
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
            else if (rs.getString(3).equals("extras"))
            {
                extrasList.add(rs.getString(1)); 
                itemPrices.put(rs.getString(1),rs.getString(2));
            }
            else if (rs.getString(3).equals("sandwich"))
            {
                sandwichList.add(rs.getString(1));
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
       sandwichButton.addActionListener(new SandwichListener());
       JButton saladButton=new JButton("Salad");
       saladButton.addActionListener(new SaladListener());
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
       chargePanel=new JPanel();
       totalDisplay=new JLabel("0.00");
       totalDisplay.setFont(new Font("Courier New", Font.PLAIN, 32));
       totalDisplay.setOpaque(true);
       totalDisplay.setForeground(Color.GREEN);
       totalDisplay.setBackground(Color.BLACK);
       JLabel totalLabel=new JLabel("Total: ");
       totalLabel.setFont(new Font("Courier New",Font.PLAIN,28));
       chargePanel.add(totalLabel);
       chargePanel.add(totalDisplay);

       chargeCancel=new JButton("Cancel");
       chargeCancel.addActionListener(new ChargeCancelListener());
       enteredAmount=new JLabel("0.00");
       enteredAmount.setFont(new Font("Courier New", Font.PLAIN,32));
       enteredAmount.setForeground(Color.GREEN);
       enteredAmount.setBackground(Color.BLACK);
       enteredAmount.setOpaque(true);
       backButton=new JButton("←");
       backButton.addActionListener(new BackListener());
       numberPanel=new JPanel();
       numberPanel.setLayout(gridBagLayout);
       
       //place number buttons onto panel
       int x=4;
       int y=1;
       
       for (int i=9;i>0;--i)
       {    
           if(i==6 || i==3 || i==0)
           {
               ++y;
               x=4;
           }
           gbc.gridx=x;
           gbc.gridy=y;
           
           JButton dynamicButton=new JButton(String.valueOf(i));
           dynamicButton.addActionListener(new NumberListener());
           numberPanel.add(dynamicButton,gbc);
           --x;
           
       }
       
       //Buttons with set payment amounts
       exactButton=new JButton("Cash \n (Exact)"); //exact amount equal to total
       exactButton.addActionListener(new ExactListener());
       ceilButton=new JButton(); //total amount ceilded
       ceilButton.addActionListener(new CeilListener());
       fiveButton=new JButton("$5"); //5$
       fiveButton.addActionListener(new FiveListener());
       tenButton=new JButton("$10"); //10$
       tenButton.addActionListener(new TenListener());
       twentyButton=new JButton("$20"); //20$
       twentyButton.addActionListener(new TwentyListener());
       cashButton=new JButton("Cash");
       cashButton.addActionListener(new CashListener());
       cashButton.setEnabled(false);
       
       gbc.gridx=0;
       gbc.gridy=0;
       numberPanel.add(twentyButton,gbc);
       gbc.gridy=1;
       numberPanel.add(tenButton,gbc);
       gbc.gridy=2;
       numberPanel.add(fiveButton,gbc);
       gbc.gridy=3;
       numberPanel.add(ceilButton,gbc);
       gbc.gridy=4;
       numberPanel.add(exactButton,gbc);
       gbc.gridx=2;
       gbc.gridy=0;
       numberPanel.add(enteredAmount,gbc);
       gbc.gridx=3;
       numberPanel.add(backButton,gbc);
       gbc.gridx=4;
       JButton chargeRemove=new JButton("Remove");
       chargeRemove.addActionListener(new removeListener());
       numberPanel.add(chargeRemove,gbc);
       
       gbc.gridx=2;
       gbc.gridy=4;
       clearButton=new JButton("Clear");
       clearButton.addActionListener(new ClearListener());
       numberPanel.add(clearButton,gbc);
       gbc.gridx=3;
       JButton zeroButton=new JButton("0");
       zeroButton.addActionListener(new NumberListener());
       numberPanel.add(zeroButton,gbc);
       gbc.gridx=4;
       numberPanel.add(chargeCancel,gbc);
       gbc.gridx=2;
       gbc.gridy=5;
       numberPanel.add(cashButton,gbc);
       
       //GUI Code for Change Display
       changeFrame=new JFrame("Change");
       changeFrame.setResizable(false);
       changeFrame.setSize(350,350);
       changeFrame.setLocationRelativeTo(null);
       changeFrame.dispatchEvent(new WindowEvent(changeFrame, WindowEvent.WINDOW_CLOSING));
       changePanel=new JPanel();
       changeLabel=new JLabel("CHANGE: ");
       changeDisplay=new JLabel("0.00");
       changeDisplay.setFont(new Font("Courier New", Font.PLAIN,32));
       changeDisplay.setForeground(Color.GREEN);
       changeDisplay.setBackground(Color.BLACK);
       changeDisplay.setOpaque(true);
       changePanel.add(changeLabel);
       changePanel.add(changeDisplay);
       changeFrame.getContentPane().add(BorderLayout.NORTH,changePanel);
       JButton saleComplete=new JButton("OK");
       saleComplete.addActionListener(new SaleCompleteListener());
       changeFrame.getContentPane().add(BorderLayout.SOUTH,saleComplete);

       //Sandwich option GUI Code
       sandwichPanel=new JPanel();
       sandwichPanel.setLayout(gridBagLayout);
       int tempValue=0;
       int tempValue2=1;
       
       JButton sBackButton=new JButton("Back");
       sBackButton.addActionListener((ActionEvent ev) -> {
           saladActive=false;
           mainFrame.remove(sandwichPanel);
           mainFrame.getContentPane().add(BorderLayout.SOUTH,orderPanel);
           mainFrame.getContentPane().add(BorderLayout.NORTH,salePanel);
           mainFrame.getContentPane().add(BorderLayout.CENTER,buttonPanel);
           mainFrame.repaint();
           mainFrame.revalidate();
       });
       
       gbc.gridx=0;
       gbc.gridy=0;
       sandwichPanel.add(sBackButton,gbc);
       
       for (int i=0;i<sandwichList.size();++i)
       {
           JButton component=new JButton((String)sandwichList.get(i));
           component.addActionListener(this);
           
           if (tempValue2==4)
           {
               ++tempValue;
               tempValue2=0;
           }
           
           gbc.gridx=tempValue;
           gbc.gridy=tempValue2;
           sandwichPanel.add(component,gbc);
           ++tempValue2;
       }
       
    }
    
    public void actionPerformed(ActionEvent ev)
    {
        String item=ev.getActionCommand();
        String itemPrice=(String)itemPrices.get(item);
        
        if (saladActive==true)
        {   
            double priceIncrease=Double.parseDouble(itemPrice)+1;
            itemPrice=String.format("%.2f",priceIncrease);
            dlm.addElement(item+"-"+itemPrice);
            orderList.setModel(dlm);
        }
        else
        {
            dlm.addElement(item+"-"+itemPrice);
            orderList.setModel(dlm);
        }
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
    
    public class SandwichListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            mainFrame.remove(salePanel);
            mainFrame.remove(buttonPanel);
            mainFrame.getContentPane().add(BorderLayout.CENTER,sandwichPanel);
            mainFrame.repaint();
            mainFrame.revalidate();
        }
    }
    
    public class SaladListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            mainFrame.remove(salePanel);
            mainFrame.remove(buttonPanel);
            mainFrame.getContentPane().add(BorderLayout.CENTER,sandwichPanel);
            mainFrame.repaint();
            mainFrame.revalidate();
            saladActive=true;
        }
    }
    
    public class CashListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            double amountEntered=Double.parseDouble(enteredAmount.getText());
            double totalAmount=Double.parseDouble(totalDisplay.getText());
            
            double change=amountEntered-totalAmount;
            
            changeDisplay.setText(String.format("%.2f",change));
            
            changeFrame.setVisible(true);
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
            enteredAmount.setText("0.00");
            clearButton.doClick();
            shutdown=true;
            transactionComplete=true;
            
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
            a=new Thread(new CalculateTotal());
            b=new Thread(new  CheckAmount());
            shutdown=false;
            transactionComplete=false;
            mainFrame.remove(orderPanel);
            mainFrame.remove(salePanel);
            mainFrame.remove(buttonPanel);
            mainFrame.remove(sandwichPanel);
            mainFrame.getContentPane().add(BorderLayout.NORTH,chargePanel);
            tempPanel=new JPanel();
            tempPanel.setLayout(new BoxLayout(tempPanel,BoxLayout.Y_AXIS));
            tempPanel.add(Box.createVerticalStrut(80));
            mainFrame.getContentPane().add(BorderLayout.SOUTH,tempPanel);
            mainFrame.getContentPane().add(BorderLayout.CENTER,numberPanel);
            mainFrame.repaint();
            mainFrame.revalidate();
            a.start(); //run thread to calculate total amount to display 
            b.start(); //run thread to check when transaction is done, and if
                      //entered amount is sufficient
        }
    }
    
    public class CheckAmount implements Runnable
    {
        public synchronized void run()
        {
            while(!transactionComplete)
            {
                Double enteredPrice=Double.parseDouble(enteredAmount.getText());
                Double total=Double.parseDouble(totalDisplay.getText());
                if (total != 0 && enteredPrice>=total)
                {
                    cashButton.setEnabled(true);
                }
                else
                {
                    cashButton.setEnabled(false);
                }
            }
        }
    }
    
    //Class for calculating the total price
    public class CalculateTotal implements Runnable
    {
        public synchronized void run()
        {   
            while(!shutdown){
            try{
                ListModel model=orderList.getModel();
                int numItems=orderList.getModel().getSize();
                total=0;
            
            
                for (int i=0;i<numItems;++i)
                {   
                    String temp=(String)model.getElementAt(i);
                    String []itemAndPrice=temp.split("-");
                    double temp2=Double.parseDouble(itemAndPrice[1]);

                    total=total+temp2;
                }
            
                String amountToDisplay=String.format("%.2f",total);
                totalDisplay.setText(amountToDisplay);
                ceilButton.setText("$"+String.valueOf((int)Math.ceil(total)));
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public class FiveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            enteredAmount.setText("5.00");
        }
    }
    
    public class ExactListener implements ActionListener
    {   
        public void actionPerformed(ActionEvent ev)
        {
            enteredAmount.setText(totalDisplay.getText());
            changeFrame.setVisible(true);
            transactionComplete=true;
            dlm.removeAllElements();
            orderList.setModel(dlm);
            changeFrame.dispose();
            chargeCancel.doClick();
        }
    }
    
    public class CeilListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
            double setText=(Math.ceil(Double.parseDouble(totalDisplay.getText())));
            String amount=String.format("%.2f",setText);
            enteredAmount.setText(amount);
        }
    }
    
    public class TenListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            enteredAmount.setText("10.00");
        }
    }
    
    public class TwentyListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            enteredAmount.setText("20.00");
        }
    }
    
    public class ClearListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            enteredAmount.setText("0.00");
            for (int i=0;i<10;++i)
            {
                amount[i]=null;
            }
            
            cashButton.setEnabled(false);
        }
    }
    
    public class SaleCompleteListener implements ActionListener
    {
         public void actionPerformed(ActionEvent ev)
         {
             changeFrame.dispose();
             chargeCancel.doClick();
             dlm.removeAllElements();
             orderList.setModel(dlm);
         }
    }
    
    public class BackListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            String toDisplay="";
            
            if (amount[amount.length-1]==null)
            {
                return;
            }
            
            for (int i=0;i<amount.length;++i)
            {
                if (amount[i]!=null)
                {
                    amount[i]=null;
                    break;
                }
            }
            
            for (int i=0;i<10;++i)
            {
                if (amount[i]!=null)
                {
                    toDisplay=toDisplay+amount[i];
                }
            }
            
            toDisplay=new StringBuffer(toDisplay).reverse().toString();
            
            if(toDisplay.length()>2)
            {
                toDisplay=new StringBuilder(toDisplay).insert(toDisplay.length()-2, ".").toString();
            }
            else if (toDisplay.length()==0)
            {
                enteredAmount.setText("0.00");
                return;
            }
            
            enteredAmount.setText(toDisplay);
        }
    }
    
    public class NumberListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            String number=ev.getActionCommand();
            String toDisplay="";
            
            for (int i=9;i>-1;--i)
            {
                if (amount[i]==null)
                {
                    amount[i]=number;
                    break;
                }
            }
            
            for (int i=0;i<10;++i)
            {
                if (amount[i]!=null)
                {
                    toDisplay=toDisplay+amount[i];
                }
            }
            
            toDisplay=new StringBuffer(toDisplay).reverse().toString();
            
            if(toDisplay.length()>2)
            {
                toDisplay=new StringBuilder(toDisplay).insert(toDisplay.length()-2, ".").toString();
            }
            
            enteredAmount.setText(toDisplay);
            
        }
    }
    
    public static void main (String [] args)
    {
        Sale sale=new Sale();
        sale.go();
    }

    
    
}
