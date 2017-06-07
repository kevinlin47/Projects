/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Kevin Lin
 */
public class ChatServer {
    
    
   //Array List to hold all client output streams
    ArrayList clientOutputStreams;
    
    public void go()
    {  
        clientOutputStreams=new ArrayList();
        try{
        //create server @ port 5000
        ServerSocket serverSocket=new ServerSocket(8080); 
        
        while (true)
        {
        //accept client connection
         Socket clientSocket= serverSocket.accept();
         
         //Chain print writer with socket output stream
         PrintWriter writer=new PrintWriter(clientSocket.getOutputStream());
         
         //add output stream to array list
         clientOutputStreams.add(writer);
         
         //create new thread to handle client requests and start thread
         Thread t=new Thread(new ClientHandler(clientSocket));
         t.start();
         
         //let user know server got a connection
         System.out.println("Got a connection");
         
        }
        
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
    }
    
    public class ClientHandler implements Runnable
    {   
        BufferedReader reader;
        Socket sock;
        
        //constructor, takes a socket parameter
        ClientHandler(Socket clientSocket)
        {
            sock=clientSocket;
            try
            {   
                //Create InputStream reader chained with socket input stream
                InputStreamReader isReader=new InputStreamReader(sock.getInputStream());
                
                //assign reader to a buffered reader chained with the InputStreamReader
                reader=new BufferedReader(isReader);
            } catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        
        public void run()
        {
            String message;
            
            try{
                while((message=reader.readLine())!=null)
                {
                    System.out.println("Read "+message);
                    distributeMessage(message);
                }
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
                    
        }
    }
    
    public void distributeMessage(String message)
    {   
        Iterator it=clientOutputStreams.iterator();
        
        //Send message to all clients 
        while(it.hasNext())
        {
            try
            {
                PrintWriter writer=(PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String args[])
    {
        ChatServer server=new ChatServer();
        server.go();
    }
}

