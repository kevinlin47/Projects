/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author Kevin Lin
 */
public class ChatClient {
    
    //GUI components 
    JFrame frame;
    JFrame usernameFrame;
    JTextArea incoming;
    JTextField userName;
    JTextField outgoing;
    JLabel usernameLabel;
    JButton ok;
    JButton sendMessage;
    
    //Socket for server connection
    Socket sock;
    
    //Reader and writer to read and send messages
    BufferedReader reader;
    PrintWriter writer;
    
    //Username String
    String username="";
    
    public void go()
    {   
        //building the GUI
        frame=new JFrame("Chat Client");
        usernameFrame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        usernameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel sidePanel=new JPanel();
        userName=new JTextField(20);
        userName.setText("");
        usernameLabel=new JLabel ("Enter Username: ");
        ok=new JButton("Ok");
        ok.addActionListener(new UsernameSubmitAction());
        usernameFrame.getRootPane().setDefaultButton(ok);
        sidePanel.add(usernameLabel);
        sidePanel.add(userName);
        sidePanel.add(ok);
        JPanel mainPanel=new JPanel();
        incoming=new JTextArea(15,50);
        outgoing=new JTextField(20);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane scroller=new JScrollPane(incoming);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sendMessage=new JButton("Send");
        sendMessage.addActionListener(new sendButtonListener());
        frame.getRootPane().setDefaultButton(sendMessage);
        mainPanel.add(scroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendMessage);
        


        
        frame.setSize(800,500);
        usernameFrame.setSize(400,100);
        usernameFrame.getContentPane().add(BorderLayout.CENTER,sidePanel);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        
        //Call server connection method
        setupConnection();
        
        //display username frame 
        usernameFrame.setVisible(true);
        

    }
    
    public void setupConnection()
    {   
        try{
        //change IP Address to server machine IP Address
        sock=new Socket("10.0.0.2",8080);
        writer=new PrintWriter(sock.getOutputStream());
        InputStreamReader streamReader=new InputStreamReader(sock.getInputStream());
        reader=new BufferedReader(streamReader);
        System.out.println("Network Established");
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public class IncomingReader implements Runnable
    {   
       
       
        public void run()
        {   
            String message;
            
            try{
                while((message=reader.readLine())!=null)
                {
                    System.out.println("Read "+message);
                    incoming.append(message+"\n");
                }
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    public class UsernameSubmitAction implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {   
            //get entered username
            username=userName.getText();
            userName.setText("");
            userName.requestFocus();
            
            //if a username was entered 
            if (!username.equals(""))
            {   
                Thread t=new Thread(new IncomingReader());
                t.start();
                frame.setVisible(true);
                usernameFrame.dispose();
            }
            else
            {
                frame.setVisible(false);
            }

        }
    }
    
    public class sendButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            try{
                String message;
                message=outgoing.getText(); //set message equal to string entered
                writer.println(username+": "+message); //print message to server
                writer.flush(); //flush out buffer
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
            outgoing.setText(""); //set text area to blank
            outgoing.requestFocus();
        }
    }

    
    public static void main(String[] args) {
        ChatClient client=new ChatClient();
        client.go();
    }
    
}
